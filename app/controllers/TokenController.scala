package controllers

import models._
import play.api.libs.json._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import org.mindrot.jbcrypt.BCrypt

object TokenController extends Controller {

    case class TokenRequest(email: String, password: String)

    implicit val createTokenRequest = Json.reads[TokenRequest]
    implicit val writeToken = Json.writes[Token]

    def token(userid: Long) = Action.async {
        Token.getTokenOrCreateNew(userid).map(res =>
            Ok(Json.toJson(res))
        )
    }

    def authorize = Action.async(parse.json) { implicit request =>
        request.body.validate[TokenRequest] match {
            case JsSuccess(tokenRequest, _) => {
                User.getUserByEmail(tokenRequest.email).flatMap(user =>
                    user match {
                        case Some(user) => {
                            if (BCrypt.checkpw(tokenRequest.password, user.password)) {
                                Token.getTokenOrCreateNew(user.id).map(res =>
                                    Ok(Json.toJson(res))
                                )
                            } else {
                                Future(Unauthorized)
                            }
                        }
                        case None => Future(NotFound)
                    }
                )
            }
            case JsError(errors) => {
                Future(BadRequest)
            }
        }
    }

}