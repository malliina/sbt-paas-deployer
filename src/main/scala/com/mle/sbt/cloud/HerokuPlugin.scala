package com.mle.sbt.cloud

import com.mle.sbt.cloud.HerokuKeys._
import java.nio.file.Paths
import sbt._
import Keys._
import PaasKeys._

/**
 * @author Michael
 */
object HerokuPlugin {
  val Heroku = config("heroku")
  val settings = Seq(
    git := Paths.get( """C:\Program Files (x86)\Git\bin\git.exe"""),
    herokuCreate <<= (heroku, streams) map ((herokuPath, logger) => {
      val command = Seq(herokuPath.toAbsolutePath.toString, "create")
      ExeUtils.execute(command, logger)
    })
  ) ++ inConfig(Heroku)(Seq(
    paasPush in Heroku <<= (git, streams) map ((gitPath, logger) => {
      val command = Seq(gitPath.toAbsolutePath.toString, "push", "heroku", "master")
      ExeUtils.execute(command, logger)
    }),
    paasUpdate in Heroku <<= paasPush in Heroku
  ))
}
