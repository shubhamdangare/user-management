
package com.example

import scalikejdbc._


class GroupDBService {

  val dBConnection = new DBConnection


  def getGroupFromActualDB(ids: Int): Option[Group] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = Group.syntax("m")

    withSQL {
      select.from(Group as m).where.eq(m.id, ids)
    }.map(Group(m.resultName)).single().apply()
  }

  def getGroupsFromActualDB: Option[List[Group]] = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = Group.syntax("m")

    Option(withSQL {
      select.from(Group as m)
    }.map(Group(m.resultName)).list().apply())
  }

  def deleteFromGroupDB(ids: Int): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = Group.syntax("m")

    withSQL {
      delete.from(Group).where.eq(m.id, ids)
    }.update.apply()
    // sql"delete from User where ids = ${ids}".update.apply()
  }

  def updatedGroupDB(ids: Int, name: String): Long = {
    dBConnection.createConnectiontoDB()
    implicit val session = AutoSession
    val m = Group.syntax("m")
    withSQL {
      update(Group).set(
        Group.column.name -> name
      ).where.eq(Group.column.id, ids)
    }.update.apply()
    //  sql"update User set permission = ${permission} where ids = ${ids}".update.apply()
  }

}
