package com.example
import org.scalatest.WordSpec

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class GroupDBServiceSpec extends WordSpec {

  val groupObj = new GroupDBService

  "A Group" should {

    "Return value for getGroup" in {

       val result = Await.result(groupObj.getGroup(1),5000 millis)
       val groupOne = Group(1,"1212aa")
       assert(result.get == groupOne)
    }


    "Not Return Empty List of Group" in {

      val result = Await.result(groupObj.getGroups,5000 millis)


      val groupList:List[Group] = List[Group]()
      assert(result != groupList)
    }



    "Return List of Group" in {

      val mockedUserGroupList: List[Group] = List(
        Group(1, "1212aa"),
        Group(2, "1212ab"),
        Group(3, "1212ac"),
        Group(4, "1212ad")
      )

      val result = Await.result(groupObj.getGroups,5000 millis)
      assert(result === mockedUserGroupList)
    }

  }
}

