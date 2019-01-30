package com.example

/**
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.{Await}
import scala.concurrent.duration._

object main extends App{
   val listToAddUsers:List[User] = List()
   val getuserGroup = new GetUserOfGroup
   //val detail = getuserGroup.getUserFromGroups(1,listOfSameGroupUsers = listToAddUsers)
   //println(detail)

  getuserGroup.getUsersGroup(1) onComplete{
    case Success(users) => println(users)
  }

  val checkPermission = new GetUserFromPermission
  checkPermission.getPermission("pqr") onComplete{
    case Success(getUser) => println(getUser)
  }



  val groupObj = new GroupDBService
  val groupInfo = groupObj.getGroup(1)
  groupInfo onComplete{
    case Success(value) => println(value)
  }
  val groupListInfo = groupObj.getGroups
}
  */

import java.sql.{Connection,DriverManager}

object main extends App {

  // connect to the database named "mysql" on port 8889 of localhost
  val url = "jdbc:mysql://localhost:3306/mysql"
  val driver = "com.mysql.jdbc.Driver"
  val username = "root"
  val password = "l3n60888"
  var connection:Connection = _
  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT host, user FROM user")
    while (rs.next) {
      val host = rs.getString("host")
      val user = rs.getString("user")
      println("host = %s, user = %s".format(host,user))
    }
  } catch {
    case e: Exception => e.printStackTrace
  }
  connection.close

}