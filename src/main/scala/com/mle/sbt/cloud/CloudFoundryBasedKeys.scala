package com.mle.sbt.cloud

import sbt._
import java.nio.file.Path

/**
 * @author Michael
 */
trait CloudFoundryBasedKeys {
  val memoryMb = SettingKey[Int]("cf-ram", "amount of ram to allocate for the instance")
  val instances = SettingKey[Int]("cf-instances", "number of instances")
  val packagedApp = TaskKey[Path]("cf-package", "packaged app to deploy (.zip, ...)")
  val runtime = SettingKey[AppRuntime]("cf-runtime", "app runtime")
  val framework = SettingKey[AppFramework]("framework", "app framework")
  val cmdLineTool = SettingKey[Path]("executable", "path to af.bat (AppFog) or vmc.bat (CloudFoundry)")
  val deployUrl = SettingKey[String]("url", "url to the app when deployed")
  val pushOptions = SettingKey[Seq[String]]("params", "parameters to executable, excluding command and app name")
  val updateCommand = TaskKey[Seq[String]]("update-command", "the command used to update the app")
  val printUpdate = TaskKey[Unit]("update-print", "prints the update command parameters")
  val pushCommand = TaskKey[Seq[String]]("push-command", "the command used to push the app")
  val printPush = TaskKey[Unit]("push-print", "prints the update command parameters")
  val help = TaskKey[String]("paas-help", "shows settings and keys")
  val login = TaskKey[Unit]("cf-login", "execute login procedure (interactive)")
}

object CloudFoundryBasedKeys extends CloudFoundryBasedKeys



