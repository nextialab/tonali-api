package models

import play.api.db.slick.DatabaseConfigProvider
import play.api.Play
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

case class User(id: Long, email: String, password: String)

class UserTableDef(tag: Tag) extends Table[User](tag, "user") {

	def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
	def email = column[String]("email")
	def password = column[String]("password")

	override def * = (id, email, password) <> ((User.apply _).tupled, User.unapply)

}

object User {

	val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

	val users = TableQuery[UserTableDef]

    def getUser(id: Long): Future[Option[User]] = {
		dbConfig.db.run(users.filter(_.id === id).result.headOption)
    }

    def getUserByEmail(email: String): Future[Option[User]] = {
        dbConfig.db.run(users.filter(_.email === email).result.headOption)
    }

}