
package com.example

import org.scalatest.WordSpec
import org.scalatest.{Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Span,Seconds}

class UserDBServiceSpec extends WordSpec with ScalaFutures with Matchers {
  val userObj = new UserDBService

  implicit val defaultPatience =PatienceConfig(timeout = Span(2, Seconds), interval = Span(2,Seconds))

  "A User" should {
    "Return value for getUser" in {

      whenReady(userObj.getUserFromActualDB(1)) {
        result => result.get shouldEqual User(1, "asdasd1", "asdasd1", "1", "root")
      }
    }

    "Not Return Empty List of Users" in {
      val userList: List[User] = List[User]()
      val result = userObj.getUsersFromActualDB
      assert(result != userList)

    }

    "Return List of Users" in {
      val mockedUserList: List[User] = List(
        User(1, "asdasd1", "asdasd1", "1", "root"),
        User(3, "asdasd3", "asdasd3", "4", "Super"),
        User(2, "asdasd2", "asdasd2", "1", "abc"))

      val result = userObj.getUsersFromActualDB
      assert(result.get === mockedUserList)
    }

    "update user in database " in {
      whenReady(userObj.updatedUserDB(3,"Super")) {
        result => assert(result === 1)
      }

    }
  }
}


