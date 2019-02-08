package com.exapmle

import java.time.Duration
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

import com.example.User

import scala.collection.JavaConverters._

class Consumer {
  println("hi from consumer")
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", classOf[CustomDeserializer])
  // import java.util.UUID
  // props.put("group.id", UUID.randomUUID.toString)
  props.put("auto.offset.reset", "latest")
  props.put("group.id", "consumer-group")
  val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](props)
  consumer.subscribe(util.Arrays.asList("user-add"))
  /** while (true) {
    * print("enter")
    * val record = consumer.poll(Duration.ZERO).asScala.iterator
    * println("true")
    * println(record)
    * for (value <- record)
    * println(value)
    * }
    */

  val record = consumer.poll(100000).asScala.toList.map(_.value())
  record
  println("consumer completed")
  consumer.close()
}