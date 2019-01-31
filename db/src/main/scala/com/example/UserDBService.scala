
package com.example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalikejdbc._


class UserDBService {
  val DB = new DBConnection

  val mockedUserList: List[User] = List(
    User(1, "asdasd1", "asdasd1", 1,"abc"),
    User(2, "asdasd2", "asdasd2", 1,"abc"),
    User(3, "asdasd3", "asdasd3", 4,"pqr"),
    User(4, "asdasd4", "asdasd4", 2,"pqr"))



  def create(implicit s: DBSession = AutoSession):Future[Long] = Future {

    DB.createConnectiontoDB()
    sql"""insert into User values (3, "Pop", "Chris","1","abc")""".updateAndReturnGeneratedKey.apply()

  }

  def getUser(id: Int): Future[Option[User]] = Future {

    Future.successful(create)
    mockedUserList.find(_.ids.equals(id)) }

  def getUsers: Future[List[User]] = Future { mockedUserList }
}



