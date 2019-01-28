package com.example

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.concurrent._
import ExecutionContext.Implicits.global


class GetUserOfGroup {

  val userObj = new UserDBService

  val ListUsers:ListBuffer[User] = ListBuffer.empty[User]

  def getUserFromGroups(id:Int) = Future{

    userObj.getUsers foreach( _ => {
      userObj.getUser(id) onComplete{
        case Success(value) => value match {
          case Some(v) => ListUsers += v
          case None =>
        }
        case Failure(exception) => println("Could not process file")
      }
    })
  }
}

