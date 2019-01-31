
package com.example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserService(userObj:UserDBService) {

  /** def getUserFromGroups(id: Int,
    * listOfUsers: List[User] = userObj.mockedUserList,
    * listOfSameGroupUsers: List[User]): List[User] = {
    * require(listOfUsers.nonEmpty, "list must not be empty")
    * val head = listOfUsers.head
    * val tail = listOfUsers.tail
    * *
    * if (tail.isEmpty)
    * listOfSameGroupUsers
    * else {
    * if (head.groupId.equals(id))
    * getUserFromGroups(id, tail, listOfSameGroupUsers.::(head))
    * else
    * getUserFromGroups(id, tail, listOfSameGroupUsers)
    * }
    * }
    */


  def getGroupUsersFromActualDB(groupId:String):List[User] = {

    userObj.getUsersFromActualDB.get.filter(_.groupId.equals(groupId))
  }


  def getGroupUsers(groupId: Int): Future[List[User]] = Future {

    userObj.mockedUserList.filter(_.groupId == groupId)
  }
}
