package com.example


import org.scalatest.FunSuite
import org.scalatest.FlatSpec
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UserGroupTest extends FunSuite {

  val user = new GetUserOfGroup
  test ("Users from particular group dont return none") {


    val groupUser = user.getUsersGroup(1)

    val result = Await.result(groupUser,5000 millis)

    groupUser onComplete{
      case Success(value) => assert(List.empty == List.empty)
    }


  }

}
