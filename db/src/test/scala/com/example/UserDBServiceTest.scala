package com.example


import org.scalatest.FunSuite
import org.scalatest.FlatSpec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UserDBServiceTest extends FunSuite {

  val user = new UserDBService
  test ("A User should return User value") {


    val userOne = user.getUser(1)

    assert(userOne != Future(Success(Some(Group(1,"1212aa")))))

  }
}
