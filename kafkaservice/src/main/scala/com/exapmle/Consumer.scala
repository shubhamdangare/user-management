package com.exapmle

import java.util
import java.util.Properties

import com.example.User
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

class Consumer {
  val config = ConfigFactory.load()
  val props = new Properties()
  props.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  props.put("key.deserializer",config.getString("KEY_DESERIALIZER"))
  props.put("value.deserializer",config.getString("VALUE_DESERIALIZER"))
  props.put("auto.offset.reset", config.getString("OFFSET"))
  props.put("group.id", config.getString("GROUP_ID"))
  val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](props)
  consumer.subscribe(util.Arrays.asList("user-add"))
  val record = consumer.poll(100000).asScala.toList.map(_.value())
  record
}