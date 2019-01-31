
package com.example

import scalikejdbc._


class UserDBService {

  val dBConnection = new DBConnection

  def getUserFromActualDB(ids: Int): Option[User] = {
    dBConnection.createConnectiontoDB()
    val user: Option[User] = DB readOnly { implicit session =>
      sql"select * from User where ids = ${ids}".map(rs => User(rs.int("ids"),
        rs.string("name"),
        rs.string("password"),
        rs.string("groupId"),
        rs.string("permission")))
        .single.apply()
    }
    user
  }

  def getUsersFromActualDB: Option[List[User]] = {
    dBConnection.createConnectiontoDB()
    val users: List[User] = DB readOnly { implicit session =>
      sql"select * from User".map(rs => User(rs.int("ids"),
        rs.string("name"),
        rs.string("password"),
        rs.string("groupId"),
        rs.string("permission")))
        .list.apply()
    }
    Option(users)
  }

  def updatedUserDB(ids: Int, permission: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    sql"update User set permission = ${permission} where ids = ${ids}".update.apply()
  }

  def deleteFromUserDB(ids: Int): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    sql"delete from User where ids = ${ids}".update.apply()
  }
}
