package org.demo

import IgnoreIOExceptionExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.nio.file.Files
import java.nio.file.Paths


class ExampleCase {
    @Test
    @ExtendWith(IgnoreIOExceptionExtension::class)
    fun test() {
        val func: () -> String = {
            "a"
        }
        assert("a" != func())
    }
    fun hll(cmd: String) : String {
        println(cmd)
        return  cmd
    }
    fun printFun(s: String, fn: (cmd: String) ->String) {
        fn(s)
    }
    @Test
    fun test2() {
        printFun("a", ::hll)
    }


    @Test
    fun readFile() {
        println(Files.readString(Paths.get("src/test/resources/test.json")))

    }
}

object BashMock {
    var run: ((cmd: String) -> String)? = null
}