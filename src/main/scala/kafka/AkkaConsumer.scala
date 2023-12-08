package kafka

import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Sink
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.common.serialization.StringDeserializer

import java.util
import java.util.Properties
import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object AkkaConsumer extends App, LazyLogging:

  implicit val system: ActorSystem = ActorSystem("consumer-sample")

  val consumerSettings =
    ConsumerSettings(system, new StringDeserializer, new StringDeserializer)

  val done = Consumer
    .plainSource(consumerSettings, Subscriptions.topics("test"))
    .runWith(Sink.foreach(println)) // just print each message for debugging

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  done.onComplete {
    case Success(_)   => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  }
