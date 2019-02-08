package com.example

import com.google.inject.AbstractModule

class EagerLoaderModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[GroupStartUpService]).asEagerSingleton
  }
}
