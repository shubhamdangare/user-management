package com.example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.{Await}
import scala.concurrent.duration._

object main extends App{
   val listToAddUsers:List[User] = List()
   val getuserGroup = new GetUserOfGroup
   //val detail = getuserGroup.getUserFromGroups(1,listOfSameGroupUsers = listToAddUsers)
   //println(detail)

  getuserGroup.getUsersGroup(1) onComplete{
    case Success(users) => println(users)
  }

  val checkPermission = new GetUserFromPermission
  checkPermission.getPermission("pqr") onComplete{
    case Success(getUser) => println(getUser)
  }



  val groupObj = new GroupDBService
  val groupInfo = groupObj.getGroup(1)
  groupInfo onComplete{
    case Success(value) => println(value)
  }
  val groupListInfo = groupObj.getGroups
}