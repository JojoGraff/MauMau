package kafka

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.stream.scaladsl.Source
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success}
import akka.kafka.scaladsl.Producer
object AkkaProducer extends App:
  implicit val system: ActorSystem = ActorSystem("producer-sample")

  val producerSettings =
    ProducerSettings(system, new StringSerializer, new StringSerializer)

  val done: Future[Done] =
    Source(1 to 100)
      .map(value => new ProducerRecord[String, String]("test", "msg " + value))
      .runWith(Producer.plainSink(producerSettings))

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  done.onComplete {
    case Success(_)   => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  }
