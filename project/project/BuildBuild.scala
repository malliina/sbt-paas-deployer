import sbt.Keys._
import sbt._

object BuildBuild extends Build {
  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.10.4",
    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1"),
    addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8")
  )
  override lazy val projects = Seq(root)
  lazy val root = Project("plugins", file("."))
}