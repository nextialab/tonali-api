name := "tonali-api"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"
libraryDependencies += "com.typesafe.play" % "play-slick" % "1.1.0"
libraryDependencies += "com.typesafe.play" % "play-slick-evolutions" % "1.1.0"