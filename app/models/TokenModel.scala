package models

import play.api.db.slick.DatabaseConfigProvider
import play.api.Play
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import java.sql.Date

case class Token(id: Long, user: Long, token: String, expires: Date, app: Long)

class TokenTableDef(tag: Tag) extends Table[Token](tag, "token") {

	def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
	def user = column[Long]("user")
	def token = column[String]("token")
	def expires = column[Date]("expires")
	def app = column[Long]("app")
	def appFK = foreignKey("app", app, App.apps)(_.id)
	def userFK = foreignKey("user", user, User.users)(_.id)

	override def * = (id, user, token, expires, app) <> ((Token.apply _).tupled, Token.unapply)

}

object Token {

}