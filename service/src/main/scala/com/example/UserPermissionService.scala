
package com.example

import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Success,Failure}

class UserPermissionService {
  val userObj = new UserDBService

  /**
    * val ListUsers: ListBuffer[User] = ListBuffer.empty[User]
    * def getUserFromPermission(givenPermission: String): Future[ListBuffer[User]] = Future {
    *userObj.mockedUserList foreach (value =>
    * if (value.permission.equals(givenPermission)) {
    *ListUsers.append(value)
    * }
    * )
    * ListUsers
    * }
    */



  def getUsersFromPermissionFromActualDB(givenPermission:String):List[User] = {

    userObj.getUsersFromActualDB.get.filter(_.permission.equals(givenPermission))
  }


  def getUsersFromPermission(givenPermission: String): Future[List[User]] = Future {
    userObj.mockedUserList.filter(_.permission == givenPermission)
  }
}
