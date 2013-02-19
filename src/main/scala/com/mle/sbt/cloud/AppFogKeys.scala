package com.mle.sbt.cloud

import sbt._

/**
 * @author Michael
 */
object AppFogKeys extends CloudFoundryBasedKeys {
  val AppFog = config("af")
  val infra = SettingKey[Infra]("af-infra", "infrastructure to run on (AWS, Rackspace, ...)")
  // todo
  val domainName = SettingKey[Option[String]]("af-domain", "domain name to use for the app, e.g. www.myapp.com")
}

