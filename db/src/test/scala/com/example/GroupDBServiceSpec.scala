
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

      val result = groupObj.getGroupsFromActualDB
      assert(result.get != groupList)
    }

    "Return List of Group" in {
      val mockedUserGroupList: List[Group] = List(
        Group(2, "1234b"),
        Group(1, "1234a"))

      val result = groupObj.getGroupsFromActualDB
        assert(result.get === mockedUserGroupList)
      }
    }
}
