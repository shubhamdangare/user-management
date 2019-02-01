name := "UserData"
organization in ThisBuild := "com.example"
scalaVersion in ThisBuild := "2.11.7"
scapegoatVersion in ThisBuild := "1.1.0"


lazy val root = (project in file("."))
  .dependsOn(db, common, service,restapi)
  .aggregate(db, common, service,restapi)
  .settings(
    name := "root",
    libraryDependencies ++= commonDependencies
  )

lazy val service = (project in file("service"))
  .dependsOn(db,common)
  .settings(
    name := "Service",
    libraryDependencies ++= commonDependencies
  )

lazy val common = (project in file("common"))
  .settings(
    name := "common",
    libraryDependencies ++= commonDependencies
  )

lazy val db = (project in file("db"))
  .aggregate(common)
  .dependsOn(common)
  .settings(
    name := "DB",
    libraryDependencies ++= commonDependencies
  )

lazy val restapi = (project in file("restapi"))
  .aggregate(common,db)
  .dependsOn(common,db)
  .settings(
    name := "restapi",
    libraryDependencies ++= commonDependencies
  )


lazy val dependencies = new {

  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  val scalaMockito = "org.mockito" % "mockito-all" % "1.8.4"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.12"
  val scalike = "org.scalikejdbc" %% "scalikejdbc"       % "3.3.2"
  //val h2Driver = "com.h2database"  %  "h2"                % "1.4.197"
  //val logback = "ch.qos.logback"  %  "logback-classic"   % "1.2.3"
  val akkaJson = "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
  val akkaHttp = "com.typesafe.akka" %% "akka-http"   % "10.1.7"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.19"


}


 lazy val commonDependencies = Seq(
   dependencies.scalaTest,
   dependencies.scalaMockito,
   dependencies.mysql,
   dependencies.scalike,
 //  dependencies.h2Driver
  // dependencies.logback
   dependencies.akkaHttp,
   dependencies.akkaStream,
   dependencies.akkaJson
 )

