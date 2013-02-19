package com.mle.sbt.cloud

import sbt._
import java.nio.file.Path

/**
 * @author Michael
 */
trait CloudFoundryBasedKeys {
  // common
  val appName = SettingKey[String]("cf-name", "name of app in the cloud")
  val memoryMb = SettingKey[Int]("cf-ram", "amount of ram to allocate for the instance")
  val instances = SettingKey[Int]("cf-instances", "number of instances")
  val packagedApp = TaskKey[Path]("cf-package", "packaged app to deploy (.zip, ...)")
  val runtime = SettingKey[AppRuntime]("runtime", "app runtime")
  // specific to tool (af/vmc)
  val framework = SettingKey[AppFramework]("framework", "app framework")
  val cmdLineTool = SettingKey[Path]("executable", "path to af.bat (AppFog) or vmc.bat (CloudFoundry)")
  val url = SettingKey[String]("url", "url to the app when deployed")
  val pushOptions = SettingKey[Seq[String]]("params", "parameters to executable, excluding command and app name")
  val updateCommand = TaskKey[Seq[String]]("update-command", "the command used to update the app")
  val printUpdate = TaskKey[Unit]("update-print", "prints the update command parameters")
  val cfUpdate = TaskKey[String]("cf-update", "deploys the updated version of the app")
  val pushCommand = TaskKey[Seq[String]]("push-command", "the command used to push the app")
  val printPush = TaskKey[Unit]("push-print", "prints the update command parameters")
  val cfPush = TaskKey[String]("push", "deploys the updated version of the app")
}

object CloudFoundryBasedKeys extends CloudFoundryBasedKeys



