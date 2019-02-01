
package com.example

import akka.actor.ActorSystem
import akka.http.javadsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.RouteResult.Complete
import spray.json._
import scala.concurrent.Future
import scala.io.StdIn


object Routes extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val appFormat = jsonFormat5(UserExtractor)
  val userObj = new UserDBService

  val route =

    get {
      path("User" / IntNumber) {
        id => {
          val user = userObj.getUserFromActualDB(id).get
          complete(s"$user")
        }
      }
    } ~
      post {
        path("UserPost") {
          {
            entity(as[UserExtractor]) { user =>
              //userObj.addUsers(ids= user.ids,name = user.name,password = user.password,groupId = user.groupId,permission = user.permission)
              val Done: Int = userObj.addUsers(user.ids, user.name, user.password, user.groupId, user.permission)
              complete(s"done")
            }
          }
        }
      }


  val bindingFuture = Http().bindAndHandle(route, "localhost", 8000)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done


}