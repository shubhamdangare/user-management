package com.example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class UserDBService {

  val mockedUserList: List[User] = List(
    User(1, "asdasd1", "asdasd1", 1,"abc"),
    User(2, "asdasd2", "asdasd2", 1,"abc"),
    User(3, "asdasd3", "asdasd3", 4,"pqr"),
    User(4, "asdasd4", "asdasd4", 2,"pqr")
  )

  def getUser(id: Int): Future[Option[User]] = Future {
    mockedUserList.find(_.ids.equals(id))
  }

  def getUsers: Future[List[User]] = Future {
    mockedUserList
  }
}
