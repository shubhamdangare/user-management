package com.example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.{Await}
import scala.concurrent.duration._


object main extends App{

  /**
  val UserService = new UserService

  val userdetails = UserService.userInfo

  userdetails onComplete{
    case Success(value) => value match {
      case Some(value) => println(value)
    }
    case Failure(e) => println(e.getStackTrace)
  }


  */

  val getuserGroup = new GetUserOfGroup
  val detail = getuserGroup.getUserFromGroups(2)

  val getPermission = new GetUserFromPermission

  val permission = getPermission.getUserFromPermission("abc")



  //Await.result(detail,5000 millis)

  permission onComplete{
    case Success(value) => println(value)
  }


}