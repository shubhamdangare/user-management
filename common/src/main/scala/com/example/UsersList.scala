package com.example

import play.api.libs.json.{Json, OWrites}

case class UsersList(users: List[UserResponse])


object UsersList {

  implicit val userResponseLIst: OWrites[UsersList] = Json.writes[UsersList]
}
