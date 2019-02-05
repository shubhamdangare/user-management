package com.example

import play.api.libs.json.{Json, OWrites}

case class GroupResponse(id: Int, name: String)

object GroupResponse {

  implicit val UsesWrites: OWrites[GroupResponse] = Json.writes[GroupResponse]

  def toDomain(groupResponse: List[Group]): List[GroupResponse] =
    groupResponse.map(groupResponse => GroupResponse(groupResponse.id, groupResponse.name))
}

