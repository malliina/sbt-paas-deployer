package com.mle.sbt.cloud

/**
 * @author Michael
 */
abstract class Infra(val name: String, val urlSuffix: String)

case object EuAws extends Infra("eu-aws", ".eu01.aws.af.cm")

case object Aws extends Infra("aws", ".aws.af.cm")

case object AsiaAws extends Infra("ap-aws", ".ap01.aws.af.cm")

case object Rackspace extends Infra("rs", ".rs.af.cm")

case object Hp extends Infra("hp", ".hp.af.cm")