package com.example

import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val db = new UserDBService


  //db.insertIntoUsers
 // println(db.getUserFromActualDB(1).get)
  //println(db.getUsersFromActualDB.get)

  //db.updatedUserDB(1,"root")

 // db.deleteFromUserDB(4)

val group = new GroupDBService

  //group.insert
 println( group.getGroupFromActualDB(3))


}
