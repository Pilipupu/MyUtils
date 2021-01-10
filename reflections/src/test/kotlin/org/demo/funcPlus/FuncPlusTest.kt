package org.demo.funcPlus

import org.junit.jupiter.api.Test

class FuncPlusTest {
    val a = {
        println("a")
    }

    @Test
    fun plus() {
        var b = {
            a
            println("b")
        }()
    }
}


fun a() {
    println("a")
}