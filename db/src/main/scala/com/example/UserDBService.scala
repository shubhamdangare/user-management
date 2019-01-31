
package com.example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalikejdbc._


class UserDBService {
  val Db = new DBConnection

  val mockedUserList: List[User] = List(
    User(1, "asdasd1", "asdasd1", "1", "abc"),
    User(2, "asdasd2", "asdasd2", "1", "abc"),
    User(3, "asdasd3", "asdasd3", "4", "pqr"),
    User(4, "asdasd4", "asdasd4", "2", "pqr"))


  def create(ids: Int, name: String, password: String, groupId: String, permission: String): Future[Long] = Future {

    implicit val session = AutoSession
    Db.createConnectiontoDB()
    sql"""insert into User values (${ids}, ${name}, ${password},${groupId},${permission})"""
      .updateAndReturnGeneratedKey.apply()

  }

  def insertIntoUsers: Future[Future[Long]] = {

    Future.successful(create(1, "asdasd1", "asdasd1", "1", "abc"))
    Future.successful(create(2, "asdasd2", "asdasd2", "1", "abc"))
    Future.successful(create(3, "asdasd3", "asdasd3", "4", "pqr"))
    Future.successful(create(4, "asdasd4", "asdasd4", "2", "pqr"))
  }


  def getUserFromActualDB(ids: Int): Option[User] = {

    Db.createConnectiontoDB()
    val name: Option[User] = DB readOnly { implicit session =>
      sql"select * from User where ids = ${ids}".map(rs => User(rs.int("ids"),
        rs.string("name"),
        rs.string("password"),
        rs.string("groupId"),
        rs.string("permission")))
        .single.apply()
    }
    name
  }


  def getUsersFromActualDB: Option[List[User]] = {

    Db.createConnectiontoDB()
    val name: List[User] = DB readOnly { implicit session =>
      sql"select * from User".map(rs => User(rs.int("ids"),
        rs.string("name"),
        rs.string("password"),
        rs.string("groupId"),
        rs.string("permission")))
        .list.apply()
    }
    Option(name)
  }


  def updatedUserDB(ids: Int, permission: String): Long = {

    Db.createConnectiontoDB()
    implicit val session = AutoSession
    sql"update User set permission = ${permission} where ids = ${ids}".update.apply()
  }

  def deleteFromUserDB(ids: Int): Long = {
    Db.createConnectiontoDB()
    implicit val session = AutoSession
    sql"delete from User where ids = ${ids}".update.apply()

  }

  def getUser(id: Int): Future[Option[User]] = Future {
    mockedUserList.find(_.ids.equals(id))
  }

  def getUsers: Future[List[User]] = Future {
    mockedUserList
  }

}
