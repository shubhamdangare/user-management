package com.example

import play.api.libs.json.{Json, OWrites}

case class GroupsList(group: List[GroupResponse])


object GroupsList {

  implicit val userResponseLIst: OWrites[GroupsList] = Json.writes[GroupsList]
}
