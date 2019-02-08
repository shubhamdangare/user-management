package com.example

import com.exapmle.Consumer
import javax.inject.Singleton

@Singleton
class GroupStartUpService {
  print("hello There \n")
  val consumerService = new Consumer
  println("inside GroupStartUpService ")
  println(consumerService.record)
}
