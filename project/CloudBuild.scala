import sbt.Keys._
import sbt._

object CloudBuild extends Build {
  val utilDep = "com.mle" %% "util" % "0.63-SNAPSHOT"
  val scalaTest = "org.scalatest" %% "scalatest" % "1.9.1" % "test"

  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.9.2",
    organization := "com.mle",
    name := "sbt-paas-deployer",
    version := "0.100-SNAPSHOT",
    sbtPlugin := true,
    exportJars := false
  )
  lazy val cloudDeploy = Project("sbt-paas-deployer", file("."))
    .settings(libraryDependencies ++= Seq(utilDep, scalaTest))

}