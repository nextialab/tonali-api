package models

import play.api.db.slick.DatabaseConfigProvider
import play.api.Play
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

case class App(id: Long, name: String, version: Int, password: String)

class AppTableDef(tag: Tag) extends Table[App](tag, "app") {

	def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
	def name = column[String]("name")
	def version = column[Int]("version")
	def password = column[String]("password")

	override def * = (id, name, version, password) <> ((App.apply _).tupled, App.unapply)

}

object App {

	val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

	val apps = TableQuery[AppTableDef]

}