package controllers

import models.User
import play.api.libs.json._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object UserController extends Controller {

	implicit val writeUser = Json.writes[User]

	def user(id: Long) = Action.async {
		User.getUser(id).map(res =>
			res match {
				case Some(user) => Ok(Json.toJson(user))
				case None => NotFound
			}
		)
	}

}
