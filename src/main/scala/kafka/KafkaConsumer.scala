package kafka

import akka.NotUsed
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.{Flow, Sink}
import com.typesafe.scalalogging.LazyLogging
import dsl.model.Move
import io.circe
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer

import java.util
import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object KafkaConsumer extends App, LazyLogging:

  implicit val system: ActorSystem = ActorSystem("consumer-sample")

  private val consumerSettings =
    ConsumerSettings(system, new StringDeserializer, new StringDeserializer)

  private val mapToObject: Flow[ConsumerRecord[String, String], Move, NotUsed] =
    Flow[ConsumerRecord[String, String]]
      .map(message =>
        decode[Move](message.value()) match
          case Left(df: DecodingFailure) => throw new IllegalArgumentException(s"Error:${df.message}")
          case Left(pf: ParsingFailure)  => throw new IllegalArgumentException(s"Error:${pf.message}")
          case Right(value)              => value
      )

  val done = Consumer
    .plainSource(consumerSettings, Subscriptions.topics("test"))
    .via(mapToObject)
    .runWith(Sink.foreach(println)) // just print each message for debugging

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  done.onComplete {
    case Success(_)   => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  }
