
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

coverageEnabled := true
coverageExcludedPackages := "<empty>;App\\..*"

lazy val root = (project in file("."))
  .settings(
    name := "users-management-system"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
libraryDependencies += "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-wordspec" % "3.3.0-SNAP3" % Test

libraryDependencies += "org.postgresql" % "postgresql" % "42.5.4"