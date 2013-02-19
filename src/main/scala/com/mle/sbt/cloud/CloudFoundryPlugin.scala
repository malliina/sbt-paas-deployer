package com.mle.sbt.cloud

import CloudFoundryKeys._
import java.nio.file.Paths
import sbt.Project
import sbt.Keys._

/**
 * @author Michael
 */
object CloudFoundryPlugin extends CloudFoundryBasedPlugin {
  val settings: Seq[Project.Setting[_]] = cfBasedSettings ++ Seq(
    cmdLineTool in CloudFoundry := Paths.get( """C:\Program Files (x86)\ruby-1.9.2\bin\vmc.bat"""),
    url in CloudFoundry <<= (appName in CloudFoundry)(n => n + ".cloudfoundry.com"),
    runtime in CloudFoundry := Java7Runtime,
    pushOptions in CloudFoundry <<= (url in CloudFoundry, instances, memoryMb in CloudFoundry, framework, runtime in CloudFoundry)(
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
    pushCommand in CloudFoundry <<= (cmdLineTool in CloudFoundry, appName in CloudFoundry, packagedApp, pushOptions in CloudFoundry) map (
      (cmdPath, app, appPackage, params) => {
        toCommand(cmdPath, "push", app, appPackage) ++ params
      }),
    cfPush in CloudFoundry <<= (pushCommand in CloudFoundry, url in CloudFoundry, streams) map (executeDeploy),
    updateCommand in CloudFoundry <<= (cmdLineTool in CloudFoundry, appName in CloudFoundry, packagedApp) map (
      (cmdPath, app, appPackage) => {
        // "update" is deprectated, "push" updates if the app already exists
        toCommand(cmdPath, "push", app, appPackage)
      }),
    cfUpdate in CloudFoundry <<= (updateCommand in CloudFoundry, url in CloudFoundry, streams) map (executeDeploy),
    printUpdate in CloudFoundry <<= (updateCommand in CloudFoundry, streams) map (logIt),
    printPush in CloudFoundry <<= (pushCommand in CloudFoundry, streams) map (logIt)
  )
}
