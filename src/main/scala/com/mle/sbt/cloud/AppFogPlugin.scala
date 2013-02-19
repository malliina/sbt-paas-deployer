package com.mle.sbt.cloud

import AppFogKeys._
import sbt.Keys._
import java.nio.file.Paths
import sbt.Project

/**
 * @author Michael
 */
object AppFogPlugin extends CloudFoundryBasedPlugin {
  val settings: Seq[Project.Setting[_]] = cfBasedSettings ++ Seq(
    cmdLineTool in AppFog := Paths.get( """C:\Program Files (x86)\ruby-1.9.2\bin\af.bat"""),
    infra := EuAws,
    url in AppFog <<= (appName in AppFog, infra)((n, i) => n + i.urlSuffix),
    runtime in AppFog := JavaRuntime,
    pushOptions in AppFog <<= (url in AppFog, instances in AppFog, memoryMb in AppFog, infra, runtime in AppFog)(
      (u, inst, mem, inf, r) => {
        val params = Map(
          "--url" -> u,
          "--instances" -> inst.toString,
          "--mem" -> (mem + "M"),
          "--runtime" -> r.name,
          "--infra" -> inf.name
        )
        params.map(kv => Seq(kv._1, kv._2)).flatten.toSeq ++ Seq("--non-interactive")
      }),
    pushCommand in AppFog <<= (cmdLineTool in AppFog, appName in AppFog, packagedApp, pushOptions in AppFog) map ((cmdPath, app, appPackage, params) => {
      toCommand(cmdPath, "push", app, appPackage) ++ params
    }),
    cfPush in AppFog <<= (pushCommand in AppFog, url in AppFog, streams) map (executeDeploy),
    updateCommand in AppFog <<= (cmdLineTool in AppFog, appName in AppFog, packagedApp) map ((cmdPath, app, appPackage) => {
      toCommand(cmdPath, "update", app, appPackage)
    }),
    cfUpdate in AppFog <<= (updateCommand in AppFog, url in AppFog, streams) map (executeDeploy),
    printUpdate in AppFog <<= (updateCommand in AppFog, streams) map (logIt),
    printPush in AppFog <<= (pushCommand in AppFog, streams) map (logIt)
  )
}
