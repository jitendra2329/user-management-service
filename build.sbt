
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

coverageEnabled := true
coverageExcludedPackages := "<empty>;App\\..*"

lazy val root = (project in file("."))
  .settings(
    name := "users-management-system"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",
  "org.scalatest" %% "scalatest-wordspec" % "3.3.0-SNAP3" % Test,
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "org.postgresql" % "postgresql" % "42.5.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "com.github.tminglei" %% "slick-pg" % "0.21.1",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.21.1"
)

libraryDependencies += "org.postgresql" % "postgresql" % "42.5.4"
libraryDependencies += "com.typesafe" % "config" % "1.4.2"