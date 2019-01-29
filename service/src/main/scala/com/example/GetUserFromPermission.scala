package com.example

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent._
import ExecutionContext.Implicits.global

class GetUserFromPermission {
  val userObj = new UserDBService

  val ListUsers: ListBuffer[User] = ListBuffer.empty[User]

  def getUserFromPermission(givenPermission: String): Future[ListBuffer[User]] = Future {

    userObj.mockedUserList foreach (value =>
      if (value.permission.equals(givenPermission)) {
        ListUsers.append(value)
      }
      )
    ListUsers
  }

  def getPermission(givenPermission: String): Future[List[User]] = Future {
    userObj.mockedUserList.filter(_.permission == givenPermission)
  }
}
