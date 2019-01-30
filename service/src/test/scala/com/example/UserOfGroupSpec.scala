package com.example

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserOfGroupSpec extends WordSpec with ScalaFutures with Matchers with MockitoSugar with BeforeAndAfter {


  val user = mock[UserDBService]

  when(user.mockedUserList).thenReturn(List(
    User(1, "asdasd1", "asdasd1", 1, "abc"),
    User(2, "asdasd2", "asdasd2", 1, "abc"),
    User(3, "asdasd3", "asdasd3", 4, "pqr"),
    User(4, "asdasd4", "asdasd4", 2, "pqr")
  ))

  val userOfGroup = new UserService(user)

  val mockedListOfUsers: Future[List[User]] = Future {
    List(User(1, "asdasd1", "asdasd1", 1, "abc"),
      User(2, "asdasd2", "asdasd2", 1, "abc"))
  }

  val userObj = new UserService(user)
  val spyObj = Mockito.spy(userObj)

  Mockito.doReturn(mockedListOfUsers).when(spyObj).getGroupUsers(1)

  "User Of Group" should {
    "return value for user with same group" in {

      val usersWithSameGroupList: List[User] = List(User(1, "asdasd1", "asdasd1", 1, "abc"),
        User(2, "asdasd2", "asdasd2", 1, "abc"))

      whenReady(spyObj.getGroupUsers(1)) {
        result => assert(result == usersWithSameGroupList)
      }
    }

    "not return empty list of users" in {
      val userList: List[User] = List[User]()
      whenReady(userOfGroup.getGroupUsers(1)) {
        result => assert(result != userList)
      }
    }

    "return empty list of users is not found in same group" in {
      val userList: List[User] = List[User]()
      whenReady(userOfGroup.getGroupUsers(8)) {
        result => assert(result == userList)
      }
    }
  }
}
