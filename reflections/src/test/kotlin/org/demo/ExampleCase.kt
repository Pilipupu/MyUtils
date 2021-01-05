package org.demo

import IgnoreIOExceptionExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


class ExampleCase {
    @Test
    @ExtendWith(IgnoreIOExceptionExtension::class)
    fun test() {

        val func: () -> String = {
            "a"
        }

        assert("a" != func())
    }
}