
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

 // case class Users(ids:Int ,name:String, password:String, groupId:String, permission:String )


object Routes extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val appFormat = jsonFormat4(UserExtractor)
 // implicit val appFormat1 = jsonFormat5(Users)

  val userObj = new UserDBService
  val groupObj = new GroupDBService

  val route =
    get {
      path("user-info" / IntNumber) {
        id => {
          val user = userObj.getUserFromActualDB(id).get
          complete(s"$user")
        }
      } ~
        path("users-info") {
          val users = userObj.getUsersFromActualDB.get
          complete(s"$users")
        } ~
        path("group-info" / IntNumber) {
          id => {
            val group = groupObj.getGroupFromActualDB(id).get
            complete(s"$group")
          }
        } ~
        path("groups-info") {
          val groups = groupObj.getGroupsFromActualDB.get
          complete(s"$groups")
        }
    } ~
      post {
        path("add-user") {
          {
            entity(as[UserExtractor]) { user =>
              val doneAdding: Long = userObj.addUsers(user.name, user.password, user.groupId, user.permission)
              complete(s"Insert $doneAdding")
            }
          }
        }
      } ~
      delete {
        path("delete-user" / IntNumber) {
          id => {
            val user: Long = userObj.deleteFromUserDB(id)
            complete("Deleted")
          }
        }
      } ~
      put {
        path("UpdateUser") {
          parameters('id.as[Int], 'value.as[String]) { (id, value) => {
            val checkUpdate: Long = userObj.updatedUserDB(id, value)
            complete("Done Updating")
          }
          }
        }
      }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8000)
  println(s"Server online at http://localhost:8000/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}