package kafka

import com.typesafe.scalalogging.LazyLogging

import java.util
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters.*

object KafkaConsumer extends App, LazyLogging:
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")
  val consumer = new KafkaConsumer[String, String](props)
  val TOPIC = "test"
  consumer.subscribe(util.Collections.singletonList(TOPIC))
  while true do
    logger.info("Polling..")
    val records = consumer.poll(100)
    for record <- records.asScala do
      // println(record)
      logger.info(record.value())
