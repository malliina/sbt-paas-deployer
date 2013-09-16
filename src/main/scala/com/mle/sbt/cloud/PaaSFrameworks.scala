package com.mle.sbt.cloud

/**
 * @author Michael
 */
abstract class AppFramework(val name: String)

case object PlayFramework extends AppFramework("play")

case object JavaWebFramework extends AppFramework("java_web")

case object OtherFramework extends AppFramework("other")