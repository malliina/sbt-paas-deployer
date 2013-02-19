package com.mle.sbt.cloud

import sbt.Keys._
import CloudFoundryBasedKeys._
import sbt.Project
import java.nio.file.Path
import scala.sys.process._


/**
 * Settings common to both AppFog and CloudFoundry.
 *
 * @author Michael
 */
trait CloudFoundryBasedPlugin {
  val cfBasedSettings: Seq[Project.Setting[_]] = Seq(
    appName <<= (name)(n => n),
    memoryMb := 256,
    instances := 1,
    runtime := Java7Runtime
  )

  def toCommand(afPath: Path, command: String, app: String, appPackage: Path) =
    Seq(afPath.toAbsolutePath.toString, command, app, "--path", appPackage.toAbsolutePath.toString)

  def execute(cmd: Seq[String], logger: TaskStreams) {
    logger.log.info(cmd.mkString(" "))
    val output = Process(cmd.head, cmd.tail).lines
    output.foreach(line => logger.log.info(line))
  }

  def executeDeploy(cmd: Seq[String], url: String, logger: TaskStreams) = {
    execute(cmd, logger)
    logger.log.info("Now try: " + url)
    url
  }

  def logIt(cmd: Seq[String], logger: TaskStreams) {
    logger.log.info(cmd.toSeq.toString())
  }
}
