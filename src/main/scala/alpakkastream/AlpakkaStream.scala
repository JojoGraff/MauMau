package alpakkastream

import akka.actor.ActorSystem
import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.{Game, MaumauController}
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

  private val filterOutEmpty = Flow[String].filter(a => !a.isBlank)

  private val mapToMove: Flow[String, Move, NotUsed] =
    Flow[String]
      .map(line => DSLParser.parseMove(line))
      .map {
        case DSLParser.Success(move, _) => move
        case DSLParser.Failure(msg, _)  => throw new IllegalArgumentException(s"Parsing failed: $msg")
        case DSLParser.Error(msg, _)    => throw new IllegalArgumentException(s"Error: $msg")
      }

  private val execute: Flow[Move, String, NotUsed] = Flow[Move]
    .map(move =>
      controller.executeMove(move) match
        case Success(value)     => value
        case Failure(exception) => throw new IllegalArgumentException(s"Error: $exception")
    )

  private val printStatus = Sink.foreach(a => tui.status())

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

@main def main(): Unit = ???