package kafka

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import dsl.model.{LayMove, Move}
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import maumau.model.Card
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}
object KafkaProducer extends App:
  implicit val system: ActorSystem = ActorSystem("producer-sample")

  private val producerSettings =
    ProducerSettings(system, new StringSerializer, new StringSerializer)

  val done: Future[Done] =
    Source(1 to 100)
      .map(number => LayMove(number, Card.dJ).asInstanceOf[Move])
      .map(moveObject => moveObject.asJson.noSpaces)
      .map(value => new ProducerRecord[String, String]("test", value))
      .runWith(Producer.plainSink(producerSettings))

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  done.onComplete {
    case Success(_)   => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  }
