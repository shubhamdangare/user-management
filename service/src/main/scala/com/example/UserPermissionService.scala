
package com.example

import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global

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

  def getUsersFromPermission(givenPermission: String): Future[List[User]] = Future {
    userObj.mockedUserList.filter(_.permission == givenPermission)
  }
}
