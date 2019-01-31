
package com.example

import org.scalatest.WordSpec
import org.scalatest.Matchers
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

class GroupDBServiceSpec extends WordSpec with ScalaFutures with Matchers {
  val groupObj = new GroupDBService
  implicit val defaultPatience =PatienceConfig(timeout = Span(2, Seconds), interval = Span(2,Seconds))

  "A Group" should {
    "Return value for getGroup" in {

      val result = groupObj.getGroupFromActualDB(1)
      assert(result.get == Group(1, "1234a"))
    }

    "Not Return Empty List of Group" in {

      val groupList: List[Group] = List[Group]()

      whenReady(groupObj.getGroupsFromActualDB) {
        result => assert(result != groupList)
      }

    }

    "Return List of Group" in {
      val mockedUserGroupList: List[Group] = List(
        Group(2, "1234b"),
        Group(1, "1234a"))

      whenReady(groupObj.getGroupsFromActualDB) {
        result => assert(result.get === mockedUserGroupList)
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