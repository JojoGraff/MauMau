package kafka

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.{Flow, Source}
import akka.{Done, NotUsed}
import dsl.model.Move
import maumau.controller.{Game, MaumauController, RandomGameMove}
import maumau.model.{Deck, Pile, Player}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Random, Success}

object KafkaProducer extends App:
  implicit val system: ActorSystem = ActorSystem("producer-sample")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  private val producerSettings = ProducerSettings(system, new StringSerializer, new StringSerializer)
    .withBootstrapServers("localhost:9092")

  val random = Random()
  val deck = Deck(random)
  var game = Game(deck, Pile(Seq()), Seq(Player(), Player()))
  val maumauController = MaumauController(game)
  val playerCount = maumauController.game.players.size
  val randomGame = new RandomGameMove(random, playerCount, deck)

  private val randomAction: Flow[Int, Move, NotUsed] =
    Flow[Int]
      .map { number =>
        game = maumauController.game
        val move = randomGame.create(game)
        maumauController.executeMove(move)

        move
      }
    
  private val serializeObject: Flow[Move, String, NotUsed] =
    Flow[Move]
      .map(move => move.asInputString())
    
  var stream = Source(1 to 100)
    .via(randomAction)
    .via(serializeObject)
    .map(value => new ProducerRecord[String, String]("test", "key", value))
    .runWith(Producer.plainSink(producerSettings))

  stream.onComplete {
    case Success(_)   => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  }