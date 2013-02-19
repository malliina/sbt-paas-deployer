package com.mle.sbt.cloud

/**
 * @author Michael
 */
case class AppFramework(name: String)

case object PlayFramework extends AppFramework("play")

case object JavaWebFramework extends AppFramework("java_web")

case object OtherFramework extends AppFramework("other")