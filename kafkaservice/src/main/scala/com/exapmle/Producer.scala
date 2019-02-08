package com.exapmle

import java.util.Properties

import com.example.{User, UserRequest}
import org.apache.kafka.clients.producer._

class Producer(user:UserRequest,id:String) {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9099")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", classOf[CustomSerializer])
    val producer = new KafkaProducer[String, User](props)
    val userObject = User(id, user.name,user.password,user.groupId,user.permission)
    /**
      * for (i <- 1 to 5) {
      * val record = new ProducerRecord[String, User](topic, s"i", User(i, s"$user + $i", s"$message + $i"))
      *producer.send(record)
      * }
      */
    val record = new ProducerRecord[String, User]("user-add", user.groupId, userObject)
    producer.send(record)
    producer.close()

}


