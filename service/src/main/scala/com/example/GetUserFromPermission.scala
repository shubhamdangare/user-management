package com.example

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent._
import ExecutionContext.Implicits.global

class GetUserFromPermission {

  val userObj = new UserDBService

  val ListUsers:ListBuffer[User] = ListBuffer.empty[User]



  def getUserFromPermission(permission:String):ListBuffer[User] = {

      userObj.getUsers foreach( _ => {
        getUserPermission(permission)onComplete{
          case Success(value) => value match {
            case Some(v) => ListUsers += v
          }
          case Failure(exception) => println("Could not process file")
        }
      })
    ListUsers
  }

  def getUserPermission(permissionToCheck: String): Future[Option[User]] = Future {
        userObj.mockedUserList.find(_.permission.equals(permissionToCheck))
  }

}
