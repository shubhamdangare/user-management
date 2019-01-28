package com.example

class GroupService {
  val groupObj = new GroupDBService
  val groupInfo = groupObj.getGroup(1)
  val groupListInfo = groupObj.getGroups
}
