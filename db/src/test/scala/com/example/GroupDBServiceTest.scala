package com.example

import org.scalatest.FunSuite
import org.scalatest.FlatSpec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


class GroupDBServiceTest extends FunSuite {
  val group = new GroupDBService

  test ("A group should return group value") {


    val userOne = group.getGroup(1)

    assert(userOne != Future.successful(Some(Group(1, "1212aa"))))

  }

  test("A group to return value when id not found"){

    val userOne = group.getGroup(5)
    assert(userOne != Future(Success(None)))

  }

}
