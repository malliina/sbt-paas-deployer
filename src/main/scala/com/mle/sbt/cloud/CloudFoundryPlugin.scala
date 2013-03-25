package com.mle.sbt.cloud

import CloudFoundryKeys._
import java.nio.file.Paths
import sbt.Keys._
import sbt._

import PaasKeys._

/**
 * @author Michael
 */
object CloudFoundryPlugin extends CloudFoundryBasedPlugin {
  val settings: Seq[Project.Setting[_]] = cfBasedSettings ++ inConfig(CloudFoundry)(Seq(
    cmdLineTool := Paths.get( """C:\Program Files (x86)\ruby-1.9.2\bin\vmc.bat"""),
    deployUrl <<= (name)(n => n + ".cloudfoundry.com"),
    runtime := Java7Runtime,
    pushOptions <<= (deployUrl, instances, memoryMb, framework, runtime)(
      (u, inst, mem, f, r) => {
        val params = Map(
          "--url" -> u,
          "--instances" -> inst.toString,
          "--memory" -> (mem + "M"),
          "--framework" -> f.name,
          "--runtime" -> r.name
        )
        params.map(kv => Seq(kv._1, kv._2)).flatten.toSeq ++
          Seq("--start", "--restart", "--no-create-services", "--no-bind-services")
      }),
    pushCommand <<= (cmdLineTool, name, packagedApp, pushOptions) map (
      (cmdPath, app, appPackage, params) => {
        toCommand(cmdPath, "push", app, appPackage) ++ params
      }),
    paasPush <<= (pushCommand, deployUrl, streams) map (executeDeploy),
    updateCommand <<= (cmdLineTool, name, packagedApp) map (
      (cmdPath, app, appPackage) => {
        // "update" is deprecated, "push" updates if the app already exists
        toCommand(cmdPath, "push", app, appPackage)
      }),
    paasUpdate <<= (updateCommand, deployUrl, streams) map (executeDeploy),
    printUpdate <<= (updateCommand, streams) map (logIt),
    printPush <<= (pushCommand, streams) map (logIt),
    login <<= (cmdLineTool, streams) map (executeLogin)
  ))
}
