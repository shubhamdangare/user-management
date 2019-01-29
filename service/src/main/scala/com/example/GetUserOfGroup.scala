package com.example

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.concurrent._
import ExecutionContext.Implicits.global


class GetUserOfGroup {

  val userObj = new UserDBService

  val ListUsers:ListBuffer[User] = ListBuffer.empty[User]

  def getUserFromGroups(id:Int):Future[ListBuffer[User]] = Future{

    userObj.mockedUserList foreach(value =>
      if(value.groupId.equals(id)){
            ListUsers.append(value)
        }
      )
    ListUsers
  }

}

