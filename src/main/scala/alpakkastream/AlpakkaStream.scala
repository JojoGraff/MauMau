package alpakkastream

import akka.actor.ActorSystem
import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.{Game, MaumauController, RandomGameMove}
import maumau.model.Card.*
import maumau.model.{Card, Deck, Pile, Player}
import maumau.view.Tui

import java.nio.file.FileSystems
import scala.concurrent.duration.*
import scala.language.postfixOps
import scala.util.{Failure, Random, Success, Try}

class AlpakkaStream(using controller: MaumauController, tui: Tui) extends LazyLogging:
  implicit val system: ActorSystem = ActorSystem("alpakka-stream")

  private val fs = FileSystems.getDefault
  private val path = getClass.getResource("/exampleMoves").getPath
  private val fileSource: Source[String, NotUsed] = FileTailSource.lines(
    path = fs.getPath(path),
    maxLineSize = 8192,
    pollingInterval = 250.millis
  )

  private val filterOutEmpty = Flow[String].filter(line => !line.isBlank)

  private val mapToMove: Flow[String, Move, NotUsed] =
    Flow[String]
      .map { line =>
        logger.info(s"Read line '${line}''")
        DSLParser.parseMove(line) match
          case DSLParser.Success(move, _) => move
          case DSLParser.Failure(msg, _)  => throw new IllegalArgumentException(s"Parsing failed: $msg")
          case DSLParser.Error(msg, _)    => throw new IllegalArgumentException(s"Error: $msg")
      }

  private val execute: Flow[Move, String, NotUsed] = Flow[Move]
    .map(move =>
      controller.executeMove(move) match
        case Left(message) => throw new RuntimeException(s"Error: $message")
        case Right(value)     => value
    )

  var messageNumber = 0
  private val printStatus = Sink.foreach { _ =>
    tui.status(messageNumber)
    messageNumber += 1
  }

  def run(): Unit =
    logger.info("Execute game via saved file")

  fileSource
    .via(filterOutEmpty)
    .via(mapToMove)
    .via(execute)
    .takeWithin(2 seconds)
    .runWith(printStatus)
    .andThen {
      case Failure(exception) => logger.error("Error", exception)
      case Success(value)     => logger.info(s"Consumed file")
    }(system.dispatcher)
    .onComplete(_ => system.terminate())(system.dispatcher)

def createRandomGame(maumauController: MaumauController, wantedNumberOfMoves: Int): Unit =
  val randomGame = RandomGameMove(new Random(), maumauController.game.players.size, maumauController.game.deck)

  for i <- 1 to wantedNumberOfMoves do
    val game = maumauController.game
    val move = randomGame.create(game)
    maumauController.executeMove(move)

    println(s"${move.asInputString()}")

@main def main(): Unit =
  val random = Random()
  val deck = Deck(random)

  val pile = Pile(Seq())
  val player1 = Player(Seq())
  val player2 = Player(Seq())
  val player3 = Player(Seq())
  val player4 = Player(Seq())
  val game = Game(deck, pile, Seq(player1, player2, player3, player4))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

 //  createRandomGame(maumauController, 1000)

  val alpakkaStream = AlpakkaStream(using maumauController, tui)
  alpakkaStream.run()
