import sbt.Keys._
import sbt._

object CloudBuild extends Build {
  lazy val cloudDeploy = Project("sbt-paas-deployer", file("."))
    .settings(libraryDependencies ++= Seq(utilDep, scalaTest))

  val utilDep = "com.github.malliina" %% "util" % "1.0.0"
  val scalaTest = "org.scalatest" %% "scalatest" % "1.9.1" % "test"

  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.10.2",
    organization := "com.github.malliina",
    name := "sbt-paas-deployer",
    version := "1.0.0",
    sbtPlugin := true,
    exportJars := false,
    resolvers += "Sonatype snaps" at "http://oss.sonatype.org/content/repositories/snapshots/",
    publishTo <<= (version)(v => {
      val repo =
        if (v endsWith "SNAPSHOT") {
          "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
        } else {
          "Sonatype releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
        }
      Some(repo)
    }),
    licenses += ("BSD-style" -> url("http://www.opensource.org/licenses/BSD-3-Clause")),
    scmInfo := Some(ScmInfo(url("https://github.com/malliina/sbt-paas-deployer"), "git@github.com:malliina/sbt-paas-deployer.git")),
    credentials += Credentials(Path.userHome / ".ivy2" / "sonatype.txt"),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := (_ => false),
    pomExtra := extraPom
  )

  def extraPom = (
      <developers>
        <developer>
          <id>malliina</id>
          <name>Michael Skogberg</name>
          <url>http://mskogberg.info</url>
        </developer>
      </developers>)
}