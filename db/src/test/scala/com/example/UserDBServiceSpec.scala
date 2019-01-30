package com.example

import org.scalatest.WordSpec
import org.scalatest.{Matchers}
import org.scalatest.concurrent.ScalaFutures

class UserDBServiceSpec extends WordSpec with ScalaFutures with Matchers {
  val userObj = new UserDBService

  "A User" should {
    "Return value for getUser" in {

      whenReady(userObj.getUser(1)) {
        result => result.get shouldEqual User(1, "asdasd1", "asdasd1", 1, "abc")
      }
    }

    "Not Return Empty List of Users" in {
      val userList: List[User] = List[User]()
      whenReady(userObj.getUsers) {
        result => assert(result != userList)
      }
    }

    "Return List of Users" in {
      val mockedUserList: List[User] = List(
        User(1, "asdasd1", "asdasd1", 1, "abc"),
        User(2, "asdasd2", "asdasd2", 1, "abc"),
        User(3, "asdasd3", "asdasd3", 4, "pqr"),
        User(4, "asdasd4", "asdasd4", 2, "pqr")
      )

      whenReady(userObj.getUsers) {
        result => assert(result === mockedUserList)
      }

    }
  }
}


