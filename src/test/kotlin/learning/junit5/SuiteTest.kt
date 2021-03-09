package learning.junit5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag

@Tag("test")
interface beforeAll

open class SuiteTest : beforeAll {
    companion object {
        var count = 0

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            println("beforeAll ${count++}")
        }
    }
}

open class BeforeEachDemo {
    private var count = 0
    var a: Thread = Thread.currentThread()
    @BeforeEach
    fun beforeEach() {
        println("beforeEach ${count++}")
        println(a)
    }
}