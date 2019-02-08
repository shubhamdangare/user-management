name := "UserData"
organization in ThisBuild := "com.example"
scalaVersion in ThisBuild := "2.11.7"
scapegoatVersion in ThisBuild := "1.1.0"

lazy val root = (project in file("."))
  .dependsOn(db, common, service,restapi,kafkaservice)
  .aggregate(db, common, service,restapi,kafkaservice)
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
  .aggregate(common,kafkaservice)
  .dependsOn(common,kafkaservice)
  .settings(
    name := "DB",
    libraryDependencies ++= commonDependencies ++ Seq("com.google.inject" % "guice" % "4.2.2")
  )

lazy val restapi = (project in file("restapi"))
  .aggregate(common,db,kafkaservice)
  .dependsOn(common,db,kafkaservice)
  .settings(
    name := "restapi",
    libraryDependencies ++= commonDependencies
  )

lazy val kafkaservice = (project in file("kafkaservice"))
  .aggregate(common)
  .dependsOn(common)
  .settings(
    name := "kafkaservice",
    libraryDependencies ++=  Seq("org.apache.kafka" %% "kafka" % "2.0.0",
      "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8",
      "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8",
      "com.typesafe.play" %% "play-json" % "2.7.1",
      "de.heikoseeberger" %% "akka-http-play-json"   % "1.20.0",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
      "net.manub" %% "scalatest-embedded-kafka" % "0.14.0" % "test"
    )
  )



lazy val dependencies = new {

  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  val scalaMockito = "org.mockito" % "mockito-all" % "1.8.4"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.12"
  val scalike = "org.scalikejdbc" %% "scalikejdbc"       % "3.3.2"
  val akkaJson = "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
  val akkaHttp = "com.typesafe.akka" %% "akka-http"   % "10.1.7"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.19"
  val playJson   = "com.typesafe.play" %% "play-json" % "2.7.1"
  val akkaHttpPlayJson= "de.heikoseeberger" %% "akka-http-play-json"   % "1.20.0"

}


 lazy val commonDependencies = Seq(
   dependencies.scalaTest,
   dependencies.scalaMockito,
   dependencies.mysql,
   dependencies.scalike,
   dependencies.akkaHttp,
   dependencies.akkaStream,
   dependencies.playJson,
   dependencies.akkaJson,
   dependencies.akkaHttpPlayJson
 )

