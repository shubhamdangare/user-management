package com.exapmle

import java.util.Properties

import com.example.{User, UserRequest}
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer._

class Producer(user: UserRequest, id: String) {
  val config = ConfigFactory.load()
  val props = new Properties()
  props.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  props.put("key.serializer", config.getString("KEY_SERIALIZER"))
  props.put("value.serializer", config.getString("VALUE_SERIALIZER"))
  val producer = new KafkaProducer[String, User](props)
  val userObject = User(id, user.name, user.password, user.groupId, user.permission)
  val record = new ProducerRecord[String, User]("user-add", s"$user.groupId", userObject)
  producer.send(record)
  producer.close()
}


