import sbt._
import Keys._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

val playVersion = "2.8.19"
val macWireVersion = "2.5.8"

val playFramework = "com.typesafe.play" %% "play" % playVersion
val macwire = "com.softwaremill.macwire" %% "macros" % macWireVersion % "provided"

lazy val root = (project in file("."))
  .settings(
    name := "rest-play-api",
    libraryDependencies ++= Seq(playFramework, macwire)
  ).enablePlugins(PlayScala)

