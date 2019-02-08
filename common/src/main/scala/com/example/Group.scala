
package com.example

import scalikejdbc._

case class Group(id: Int, name: String)

object Group extends SQLSyntaxSupport[Group] {
  override val tableName = "Groups"
  override val useSnakeCaseColumnName = false

  override val nameConverters = Map(
    "^id$" -> "id",
    "^name$" -> "name"
  )

  def apply(e: SyntaxProvider[Group])(rs: WrappedResultSet): Group = apply(e.resultName)(rs)

  def apply(e: ResultName[Group])(rs: WrappedResultSet): Group =
    new Group(id = rs.int(e.id), name = rs.string(e.name))
}
