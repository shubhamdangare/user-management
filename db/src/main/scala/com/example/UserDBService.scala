
package com.example

import scalikejdbc._

import java.util.UUID

class UserDBService {

  val dBConnection = new DBConnection

  def addUsers(name: String, password: String, groupId: String, permission: String): String = {

    implicit val session = AutoSession
    dBConnection.createConnectiontoDB()

     val userId = UUID.randomUUID().toString
    withSQL {
      insert.into(User).values(userId , name, password, groupId, permission)
    }.update().apply()
    userId
  }

  def getUserFromActualDB(ids: Int): Option[User] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = User.syntax("m")

    withSQL {
      select.from(User as m).where.eq(m.ids, ids)
    }.map(User(m.resultName)).single().apply()
  }

  def getUsersFromActualDB: Option[List[User]] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = User.syntax("m")

    Option(withSQL {
      select.from(User as m)
    }.map(User(m.resultName)).list().apply())
  }

  def updatedUserDB(ids: Int, permission: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = User.syntax("m")
    withSQL {
      update(User).set(
        User.column.permission -> permission
      ).where.eq(User.column.ids, ids)
    }.update.apply()
    //  sql"update User set permission = ${permission} where ids = ${ids}".update.apply()
  }

  def deleteFromUserDB(ids: Int): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = User.syntax("m")

    withSQL {
      delete.from(User).where.eq(m.ids, ids)
    }.update.apply()
    // sql"delete from User where ids = ${ids}".update.apply()
  }
}
