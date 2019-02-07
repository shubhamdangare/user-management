
package com.example

import play.api.libs.json.{Json, OWrites}

case class UserResponse(ids: String, name: String, password: String, groupId: String, permission: String)

object UserResponse {

  implicit val UsesWrites: OWrites[UserResponse] = Json.writes[UserResponse]

  def toDomain(userResponse: List[User]): List[UserResponse] =
    userResponse.map(userResponse => UserResponse(userResponse.ids, userResponse.name, userResponse.password,
      userResponse.groupId, userResponse.permission))
}

