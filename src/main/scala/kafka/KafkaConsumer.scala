package kafka

import akka.NotUsed
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.{Flow, Sink}
import com.typesafe.scalalogging.LazyLogging
import dsl.DSLParser
import dsl.model.Move
import maumau.controller.{Game, MaumauController}
import maumau.model.{Deck, Pile, Player}
import maumau.view.Tui
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Random, Success}

object KafkaConsumer extends App, LazyLogging:
  implicit val system: ActorSystem = ActorSystem("consumer-maumau-moves")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  private val consumerSettings = ConsumerSettings(system, new StringDeserializer, new StringDeserializer)

  val kafkaTopic = "play_game"
  val kafkaKey = "move"

  val random = new Random()
  var game = Game(Deck(random), Pile(Seq()), Seq(Player(), Player()))
  val maumauController = MaumauController(game)
  val tui = Tui(maumauController)

  private val mapToObject: Flow[ConsumerRecord[String, String], Move, NotUsed] =
    Flow[ConsumerRecord[String, String]]
      .filter(message => message.key() == kafkaKey)
      .map(message =>
        logger.info(s"Message '${message.value()}''")
        DSLParser.parseMove(message.value()) match
          case DSLParser.Success(move, _) => move
          case DSLParser.Failure(msg, _)  => throw new IllegalArgumentException(s"Parsing failed: $msg")
          case DSLParser.Error(msg, _)    => throw new IllegalArgumentException(s"Error: $msg")
      )

  var messageNumber = 0
  val stream = Consumer
    .plainSource(consumerSettings, Subscriptions.topics(kafkaTopic))
    .via(mapToObject)
    .map { move =>
      tui.executeMove(move, messageNumber)
      messageNumber += 1
    }
    .runWith(Sink.ignore)
