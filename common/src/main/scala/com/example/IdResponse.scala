package com.example

import play.api.libs.json.{Json, OWrites}

case class IdResponse(id: String)

object IdResponse {

  implicit val IdResponseWrite: OWrites[IdResponse] = Json.writes[IdResponse]
}
