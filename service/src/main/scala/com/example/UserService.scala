
package com.example

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


  def getGroupUsersFromActualDB(groupId:String):Option[List[User]] = {

    Option(userObj.getUsersFromActualDB.get.filter(_.groupId.equals(groupId)))
  }

}
