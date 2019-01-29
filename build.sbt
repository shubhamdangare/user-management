name := "UserData"
organization in ThisBuild := "com.example"
scalaVersion in ThisBuild := "2.12.3"


lazy val root = (project in file("."))
  .dependsOn(db, common, service)
  .aggregate(db, common, service)
  .settings(
    name := "root",
    libraryDependencies ++= commonDependencies,
  )

lazy val service = (project in file("service"))
  .dependsOn(db)
  .settings(
    name := "Service",
    libraryDependencies ++= commonDependencies,
  )

lazy val common = (project in file("common"))
  .settings(
    name := "common",
    libraryDependencies ++= commonDependencies,
  )

lazy val db = (project in file("db"))
  .settings(
    name := "DB",
    libraryDependencies ++= commonDependencies,
  )


lazy val dependencies = new {

  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  val scalaMockito = "org.mockito" % "mockito-all" % "1.8.4"

}


 lazy val commonDependencies = Seq(
   dependencies.scalaTest,
   dependencies.scalaMockito
 )