
package com.example

import java.util.UUID

import scalikejdbc._

class UserDBService {

  val dBConnection = new DBConnection

  def addUsers(name: String, password: String, groupId: String, permission: String): String = {
    implicit val session = AutoSession
    dBConnection.createConnectiontoDB()
    val userId = UUID.randomUUID().toString
    withSQL {
      insert.into(User).values(userId, name, password, groupId, permission)
    }.update().apply()
    userId
  }

  def getUserFromActualDB(ids: String): Option[User] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val userTable = User.syntax("m")
    withSQL {
      select.from(User as userTable).where.eq(userTable.ids, ids)
    }.map(User(userTable.resultName)).single().apply()
  }

  def getUsersFromActualDB: Option[List[User]] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val userTable = User.syntax("m")
    Option(withSQL {
      select.from(User as userTable)
    }.map(User(userTable.resultName)).list().apply())
  }

  def updatedUserDB(name: String, permission: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    withSQL {
      update(User).set(
        User.column.permission -> permission
      ).where.eq(User.column.name, name)
    }.update.apply()
  }

  def deleteFromUserDB(name: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
     withSQL {
      delete.from(User).where.eq(User.column.name, name)
    }.update.apply()
  }
}
