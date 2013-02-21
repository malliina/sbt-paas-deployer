package com.mle.sbt.cloud

import com.mle.sbt.cloud.HerokuKeys._
import java.nio.file.Paths
import sbt._
import Keys._

/**
 * @author Michael
 */
object HerokuPlugin {
  val settings = Seq(
    git := Paths.get( """C:\Program Files (x86)\Git\bin\git.exe"""),
    herokuCreate <<= (heroku, streams) map ((herokuPath, logger) => {
      val command = Seq(herokuPath.toAbsolutePath.toString, "create")
      ExeUtils.execute(command, logger)
    }),
    herokuPush <<= (git, streams) map ((gitPath, logger) => {
      val command = Seq(gitPath.toAbsolutePath.toString, "push", "heroku", "master")
      ExeUtils.execute(command, logger)
    })
  )
}
