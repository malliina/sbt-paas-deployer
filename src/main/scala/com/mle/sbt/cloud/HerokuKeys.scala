package com.mle.sbt.cloud

import java.nio.file.Path
import sbt.{TaskKey, SettingKey}

/**
 * @author Michael
 */
object HerokuKeys {
  val git = SettingKey[Path]("git-path", "path to git executable")
  val heroku = SettingKey[Path]("heroku-path", "path to heroku executable")
  val herokuCreate = TaskKey[Unit]("heroku-create", "creates the application on heroku")
  val herokuPush = TaskKey[Unit]("heroku-push", "deploys the app on heroku")
}
