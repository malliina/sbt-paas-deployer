package com.mle.sbt.cloud.tests

import org.scalatest.FunSuite
import sys.process.{ProcessLogger, Process}

/**
 * @author Michael
 */
class ProcessTests extends FunSuite {
  test("process") {
    val exe = """C:\Program Files (x86)\ruby-1.9.2\bin\af.bat"""
    Process(exe, Seq("login")).!<(ProcessLogger(line => println(line)))
  }
}
