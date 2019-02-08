
package com.example

import scalikejdbc._

class GroupDBService {

  val dBConnection = new DBConnection

  def getGroupFromActualDB(ids: Int): Option[Group] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val groupTable = Group.syntax("m")
    withSQL {
      select.from(Group as groupTable).where.eq(groupTable.id, ids)
    }.map(Group(groupTable.resultName)).single().apply()
  }

  def getGroupsFromActualDB: Option[List[Group]] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val groupTable = Group.syntax("m")
    Option(withSQL {
      select.from(Group as groupTable)
    }.map(Group(groupTable.resultName)).list().apply())
  }

  def deleteFromGroupDB(ids: Int): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val groupTable = Group.syntax("m")
    withSQL {
      delete.from(Group).where.eq(groupTable.id, ids)
    }.update.apply()
  }

  def updatedGroupDB(ids: Int, name: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    withSQL {
      update(Group).set(
        Group.column.name -> name
      ).where.eq(Group.column.id, ids)
    }.update.apply()
  }
}
