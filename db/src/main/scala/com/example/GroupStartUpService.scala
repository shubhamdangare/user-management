package com.example

import com.exapmle.Consumer
import javax.inject.Singleton
import org.apache.log4j.Logger

@Singleton
class GroupStartUpService {
  val logger = Logger.getLogger(this.getClass)
  val consumerService = new Consumer
  logger.info("inside GroupStartUpService ")
  logger.info(s"${consumerService.record}")
}
