package com.example

import play.api.libs.json.{Json, Reads}

case class GroupRequest(id: Int, name: String)

object GroupRequest {

  implicit val UsesReads: Reads[GroupRequest] = Json.reads[GroupRequest]

}