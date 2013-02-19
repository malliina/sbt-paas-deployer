package com.mle.sbt.cloud

import sbt._

/**
 * @author Michael
 */
object CloudFoundryKeys extends CloudFoundryBasedKeys {
  val CloudFoundry = config("cf")
}

