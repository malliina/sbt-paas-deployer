package com.mle.sbt.cloud

import java.nio.file.Path
import sbt._

/**
 * @author Michael
 */
object HerokuKeys {
  val git = SettingKey[Path]("git-path", "path to git executable")
  val heroku = SettingKey[Path]("heroku-path", "path to heroku executable")
  val herokuCreate = TaskKey[Unit]("heroku-create", "creates the application on heroku")
  val domainAdd = TaskKey[String]("add-domain", "adds a domain")
  val rename = InputKey[String]("heroku-rename", "renames the app")
}
