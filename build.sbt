name := "UserData"
organization in ThisBuild := "com.example"
scalaVersion in ThisBuild := "2.12.3"


lazy val root = (project in file("."))
  .dependsOn(db, common, service)
  .aggregate(db, common, service)
  .settings(
    name := "root",
  )

lazy val service = (project in file("service"))
  .dependsOn(db)
  .settings(
    name := "Service",
  )

lazy val common = (project in file("common"))
  .settings(
    name := "common",
  )

lazy val db = (project in file("db"))
  .settings(
    name := "DB",
  )