package com.example

import com.exapmle.Consumer
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Singleton

@Singleton
class GroupStartUpService extends LazyLogging{
  val consumerService = new Consumer
  logger.info("inside GroupStartUpService ")
  logger.info(s"${consumerService.record}")
}
