
package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport

import scala.io.StdIn


object Routes extends App with PlayJsonSupport {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  val userDBService = new UserDBService
  val groupDBService = new GroupDBService

  val route =
    get {
      path("user-info" / IntNumber) {
        id => {
          val user = userDBService.getUserFromActualDB(id).get
          complete(UserResponse(user.ids, user.name, user.password, user.groupId, user.permission))
        }
      } ~
        path("users-info") {
          val usersList = userDBService.getUsersFromActualDB.get
          val userResponseList: List[UserResponse] = UserResponse.toDomain(usersList)
          complete(UsersList(userResponseList))
        } ~
        path("group-info" / IntNumber) {
          id => {
            val group = groupDBService.getGroupFromActualDB(id).get
            complete(s"$group")
          }
        } ~
        path("groups-info") {
          val groups = groupDBService.getGroupsFromActualDB.get
          complete(s"$groups")
        }
    } ~
      path("add-user") {
        (post & entity(as[UserRequest])) { userRequest =>
          val userId = userDBService.addUsers(
            userRequest.name,
            userRequest.password,
            userRequest.groupId,
            userRequest.permission
          )
          complete(IdResponse(userId))
        }
      } ~
      delete {
        path("delete-user" / IntNumber) {
          id => {
            val user: Long = userDBService.deleteFromUserDB(id)
            complete("Deleted")
          }
        }
      } ~
      put {
        path("UpdateUser") {
          parameters('id.as[Int], 'value.as[String]) { (id, value) => {
            val checkUpdate: Long = userDBService.updatedUserDB(id, value)
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