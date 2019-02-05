
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

}
