package com.example

import com.exapmle.Consumer
import javax.inject.Singleton

@Singleton
class GroupStartUpService {
  val consumerService = new Consumer
  println("inside GroupStartUpService ")
  println(consumerService.record)
}
