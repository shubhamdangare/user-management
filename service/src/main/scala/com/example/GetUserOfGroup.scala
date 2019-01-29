package com.example

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.concurrent._
import ExecutionContext.Implicits.global

class GetUserOfGroup {
  val userObj = new UserDBService

  def getUserFromGroups(id: Int,
                        listOfUsers: List[User] = userObj.mockedUserList,
                        listOfSameGroupUsers: List[User]): List[User] = {
    require(listOfUsers.nonEmpty, "list must not be empty")
    val head = listOfUsers.head
    val tail = listOfUsers.tail

    if (tail.isEmpty)
      listOfSameGroupUsers
    else {
      if (head.groupId.equals(id))
        getUserFromGroups(id, tail, listOfSameGroupUsers.::(head))
      else
        getUserFromGroups(id, tail, listOfSameGroupUsers)
    }
  }

  def getUsersGroup(groupId: Int): Future[List[User]] = Future {
    userObj.mockedUserList.filter(_.groupId == groupId)
  }
}
