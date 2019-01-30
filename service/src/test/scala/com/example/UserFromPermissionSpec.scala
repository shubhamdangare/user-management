package com.example

import org.scalatest.WordSpec
import org.scalatest.{Matchers}
import org.scalatest.concurrent.ScalaFutures


class UserFromPermissionSpec extends WordSpec with ScalaFutures with Matchers {
  val userPermissionObj = new UserPermissionService

  "Permission" should {
    "Return value for user With permission" in {

      val userWithPermissionList: List[User] = List(User(1, "asdasd1", "asdasd1", 1, "abc"),
        User(2, "asdasd2", "asdasd2", 1, "abc"))

      whenReady(userPermissionObj.getUsersFromPermission("abc")) {
        result => assert(result == userWithPermissionList)
      }
    }

    "Not Return Empty List of Users" in {
      val userList: List[User] = List[User]()
      whenReady(userPermissionObj.getUsersFromPermission("abc")) {
        result => assert(result != userList)
      }
    }

    "Return Empty List of Users is not found " in {
      val userList: List[User] = List[User]()
      whenReady(userPermissionObj.getUsersFromPermission("abcdd")) {
        result => assert(result == userList)
      }
    }
  }
}
