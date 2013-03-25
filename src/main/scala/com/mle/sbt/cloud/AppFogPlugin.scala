package com.mle.sbt.cloud

import AppFogKeys._
import sbt.Keys._
import java.nio.file.Paths
import PaasKeys._
import sbt._

/**
 * @author Michael
 */
object AppFogPlugin extends CloudFoundryBasedPlugin {
  val settings: Seq[Project.Setting[_]] = cfBasedSettings ++ inConfig(AppFog)(Seq(
    cmdLineTool := Paths.get( """C:\Program Files (x86)\ruby-1.9.2\bin\af.bat"""),
    infra := EuAws,
    deployUrl <<= (name, infra)((n, i) => n + i.urlSuffix),
    runtime := JavaRuntime,
    pushOptions <<= (deployUrl, instances, memoryMb, infra, runtime)(
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
    pushCommand <<= (cmdLineTool, name, packagedApp, pushOptions) map ((cmdPath, app, appPackage, params) => {
      toCommand(cmdPath, "push", app, appPackage) ++ params
    }),
    paasPush <<= (pushCommand, deployUrl, streams) map (executeDeploy),
    updateCommand <<= (cmdLineTool, name, packagedApp) map ((cmdPath, app, appPackage) => {
      toCommand(cmdPath, "update", app, appPackage)
    }),
    paasUpdate <<= (updateCommand, deployUrl, streams) map (executeDeploy),
    printUpdate <<= (updateCommand, streams) map logIt,
    printPush <<= (pushCommand, streams) map logIt,
    login <<= (cmdLineTool, streams) map executeLogin
  ))
}
