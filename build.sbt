import sbt._
import Keys._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

val playVersion = "2.8.19"

val playFramework = "com.typesafe.play" %% "play" % playVersion

lazy val root = (project in file("."))
  .settings(
    name := "rest-play-api",
    libraryDependencies ++= Seq(playFramework)
  ).enablePlugins(PlayScala)

