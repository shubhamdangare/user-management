
package com.example

import org.scalatest.WordSpec
import org.scalatest.{Matchers}
import org.scalatest.concurrent.ScalaFutures

class GroupDBServiceSpec extends WordSpec with ScalaFutures with Matchers {
  val groupObj = new GroupDBService

  "A Group" should {
    "Return value for getGroup" in {

      whenReady(groupObj.getGroup(1)) {
        result => result.get shouldEqual Group(1, "1212aa")
      }
    }

    "Not Return Empty List of Group" in {
      val groupList: List[Group] = List[Group]()
      whenReady(groupObj.getGroups) {
        result => assert(result != groupList)
      }

    }

    "Return List of Group" in {
      val mockedUserGroupList: List[Group] = List(
        Group(1, "1212aa"),
        Group(2, "1212ab"),
        Group(3, "1212ac"),
        Group(4, "1212ad"))

      whenReady(groupObj.getGroups) {
        result => assert(result === mockedUserGroupList)
      }

    }
  }
}

/**
  * *
  * val result = Await.result(groupObj.getGroup(1), 5000 millis)
  * val groupOne = Group(1, "1212aa")
  * assert(result.get == groupOne)
  * *
  * "Not Return Empty List of Group" in {
  * val result = Await.result(groupObj.getGroups, 5000 millis)
  * val groupList: List[Group] = List[Group]()
  * assert(result != groupList)
  * }
  * *
  * "Return List of Group" in {
  * val mockedUserGroupList: List[Group] = List(
  * Group(1, "1212aa"),
  * Group(2, "1212ab"),
  * Group(3, "1212ac"),
  * Group(4, "1212ad")
  * )
  * *
  * val result = Await.result(groupObj.getGroups, 5000 millis)
  * assert(result === mockedUserGroupList)
  * }
  *
  */