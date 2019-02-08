package com.exapmle

import java.util
import java.util.Properties
import com.example.User
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

class Consumer {
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", classOf[CustomDeserializer])
  props.put("auto.offset.reset", "latest")
  props.put("group.id", "consumer-group")
  val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](props)
  consumer.subscribe(util.Arrays.asList("user-add"))
  val record = consumer.poll(100000).asScala.toList.map(_.value())
  record
}