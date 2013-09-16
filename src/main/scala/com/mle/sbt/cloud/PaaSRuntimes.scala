package com.mle.sbt.cloud

/**
 * @author Michael
 */
abstract class AppRuntime(val name: String)

case object Java7Runtime extends AppRuntime("java7")

case object JavaRuntime extends AppRuntime("java")

case object OtherRuntime extends AppRuntime("other")
