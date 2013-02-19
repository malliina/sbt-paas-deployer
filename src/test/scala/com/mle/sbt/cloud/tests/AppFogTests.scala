package com.mle.sbt.cloud.tests

import org.scalatest.FunSuite
import scala.sys.process._
import com.mle.util.Log
import java.nio.file.Paths

/**
 * @author Michael
 */
class AppFogTests extends FunSuite with Log {
  test("can run test") {

  }
  test("can execute af") {
    val af = Paths.get( """C:\Program Files (x86)\ruby-1.9.2\bin\af.bat""")
    val output = Process(af.toAbsolutePath.toString, Seq("apps")).lines
    output foreach log.info
  }
}
