
package com.example

import scalikejdbc._


case class User(ids: Int, name: String, password: String, groupId: String, permission: String)

object User extends SQLSyntaxSupport[User] {
  override val tableName = "User"
  override val useSnakeCaseColumnName = false

  override val nameConverters = Map(
    "^id$" -> "ids",
    "^name$" -> "name",
    "^password$" -> "password",
    "^groupId$" -> "groupId",
    "^permission$" -> "permission")

  def apply(e: SyntaxProvider[User])(rs: WrappedResultSet): User = apply(e.resultName)(rs)

  def apply(e: ResultName[User])(rs: WrappedResultSet): User =
    new User(ids = rs.int(e.ids), name = rs.string(e.name), password = rs.string(e.password),
      groupId = rs.string(e.groupId), permission = rs.string(e.permission))

}

