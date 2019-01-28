package com.example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GroupDBService {

  val mockedUserGroupList: List[Group] = List(
    Group(1, "1212aa"),
    Group(2, "1212ab"),
    Group(3, "1212ac"),
    Group(4, "1212ad")
  )

  def getGroup(id: Int): Future[Option[Group]] = Future {
    mockedUserGroupList.find(_.ids.equals(id))
  }

  def getGroups: Future[List[Group]] = Future {
    mockedUserGroupList
  }
}
