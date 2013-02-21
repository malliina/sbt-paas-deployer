package com.mle.sbt.cloud

import sbt.Keys._
import sys.process.Process

/**
 * @author Michael
 */
object ExeUtils {
  /**
   * Executes the supplied command with the given parameters,
   * logging the command and any subsequent output using the logger's INFO level.
   *
   * @param cmd command to execute
   * @param logger the logger
   */
  def execute(cmd: Seq[String], logger: TaskStreams) {
    val output = execute2(cmd, logger)
    output.foreach(line => logger.log.info(line))
  }

  def execute2(cmd: Seq[String], logger: TaskStreams) = {
    logger.log.info(cmd.mkString(" "))
    Process(cmd.head, cmd.tail).lines
  }
}
