package com.mle.sbt.cloud

import sbt.TaskKey

/**
 * @author Michael
 */
trait PaasKeys {
  val paasPush = TaskKey[Unit]("paas-push", "deploys the app on the paas provider")
  val paasUpdate = TaskKey[Unit]("paas-update", "updates the app on the paas provider")
}

object PaasKeys extends PaasKeys