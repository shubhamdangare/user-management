
package com.example

import scalikejdbc._


class GroupDBService {

  val dBConnection = new DBConnection


  def getGroupFromActualDB(ids: Int):Option[Group] = {
    dBConnection.createConnectiontoDB()
    val groupFromDB: Option[Group] = DB readOnly { implicit session =>
      sql"select * from Groups where id = ${ids}".map(rs => Group(rs.int("id"),
        rs.string("name")))
        .single.apply()
    }
    groupFromDB
  }

  def getGroupsFromActualDB:Option[List[Group]] = {
    dBConnection.createConnectiontoDB()
    val groupFromDB: List[Group] = DB readOnly { implicit session =>
      sql"select * from Groups".map(rs => Group(rs.int("id"),
        rs.string("name")))
        .list().apply()
    }
   Option(groupFromDB)
  }

}
