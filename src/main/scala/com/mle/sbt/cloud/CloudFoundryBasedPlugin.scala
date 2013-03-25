package com.mle.sbt.cloud

import sbt.Keys._
import CloudFoundryBasedKeys._
import sbt.{ScopedTaskable, Project}
import java.nio.file.Path


/**
 * Settings common to both AppFog and CloudFoundry.
 *
 * @author Michael
 */
trait CloudFoundryBasedPlugin {
  val cfBasedSettings: Seq[Project.Setting[_]] = Seq(
    memoryMb := 256,
    instances := 1,
    runtime := Java7Runtime,
    help <<= streams map (logger => {
      describe(name, memoryMb, instances, runtime,
        help, framework, cmdLineTool, deployUrl,
        pushOptions, pushCommand, updateCommand, printPush,
        printUpdate)
    })
  )

  def describe(torks: ScopedTaskable[_]*) = torks
    .map(tork => tork.key.label + "\t\t" + tork.key.description)
    .mkString(sys.props("line.separator"))

  def toCommand(afPath: Path, command: String, app: String, appPackage: Path) =
    Seq(afPath.toAbsolutePath.toString, command, app, "--path", appPackage.toAbsolutePath.toString)

  def executeDeploy(cmd: Seq[String], url: String, logger: TaskStreams) {
    ExeUtils.execute(cmd, logger)
    logger.log.info("Now try: " + url)
  }

  def logIt(cmd: Seq[String], logger: TaskStreams) {
    logger.log.info(cmd.toSeq.toString())
  }

  def executeLogin(executable: Path, logger: TaskStreams){
    executeOneParameter(executable, "login", logger)
  }

  def executeOneParameter(executable: Path, firstParameter: String, logger: TaskStreams){
    ExeUtils.execute(Seq(executable.toAbsolutePath.toString, firstParameter), logger)
  }
}
