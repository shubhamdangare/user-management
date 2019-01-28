package com.example

class UserService {
  val userObj = new UserDBService
  val userInfo = userObj.getUser(2)
  val userListInfo = userObj.getUsers
}
