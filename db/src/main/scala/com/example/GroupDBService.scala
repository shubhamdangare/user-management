
package com.example

import scalikejdbc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GroupDBService {

  val Db = new DBConnection

  val mockedUserGroupList: List[Group] = List(
    Group(1, "1212aa"),
    Group(2, "1212ab"),
    Group(3, "1212ac"),
    Group(4, "1212ad"))

  def insertIntoDB(ids: Int, name: String): Future[Long] = Future {

    implicit val session = AutoSession
    Db.createConnectiontoDB()
    sql"""insert into Groups values (${ids}, ${name})"""
      .updateAndReturnGeneratedKey.apply()
  }

  def insert: Future[Future[Long]] = {

    Future.successful(insertIntoDB(1, "1234a"))
    Future.successful(insertIntoDB(2, "1234b"))
  }

  def getGroupFromActualDB(ids: Int):Option[Group] = {

    Db.createConnectiontoDB()
    val groupFromDB: Option[Group] = DB readOnly { implicit session =>
      sql"select * from Groups where id = ${ids}".map(rs => Group(rs.int("id"),
        rs.string("name")))
        .single.apply()
    }
    groupFromDB
  }


  def getGroupsFromActualDB:Future[Option[List[Group]]] = Future{

    Db.createConnectiontoDB()
    val groupFromDB: List[Group] = DB readOnly { implicit session =>
      sql"select * from Groups".map(rs => Group(rs.int("id"),
        rs.string("name")))
        .list().apply()
    }
   Option(groupFromDB)
  }


  def getGroup(id: Int): Future[Option[Group]] = Future {
    mockedUserGroupList.find(_.ids.equals(id))
  }

  def getGroups: Future[List[Group]] = Future {
    mockedUserGroupList
  }
}
