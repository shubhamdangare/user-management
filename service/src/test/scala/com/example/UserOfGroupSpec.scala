
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
  val userOfGroup = new UserService(user)
  val mockedListOfUsers: Option[List[User]] = {
    Option(List(User(1, "asdasd1", "asdasd1", "1", "root"),
      User(2, "asdasd2", "asdasd2", "1", "abc")))
  }
  val userObj = new UserService(user)
  val spyObj = Mockito.spy(userObj)

  Mockito.doReturn(mockedListOfUsers).when(spyObj).getGroupUsersFromActualDB("1")

  "User Of Group" should {
    "return value for user with same group" in {

      val usersWithSameGroupList: List[User] = List(User(1, "asdasd1", "asdasd1", "1", "root"),
        User(2, "asdasd2", "asdasd2", "1", "abc"))

      val result = spyObj.getGroupUsersFromActualDB("1")
      assert(result.get == usersWithSameGroupList)
    }

    "not return empty list of users" in {
      val userList: List[User] = List[User]()
      val result = spyObj.getGroupUsersFromActualDB("1")
      assert(result.get != userList)
    }

    "return empty list of users is not found in same group" in {

      val userList: List[User] = List[User]()
      val userService = new UserDBService
      val userUnMockedObj = new UserService(userService)
      val result = userUnMockedObj.getGroupUsersFromActualDB("8").getOrElse("asd")
      assert(result == userList)
    }
  }
}
