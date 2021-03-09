package learning.junit5

import org.junit.jupiter.api.Test

class TestBeforeEach : BeforeEachDemo() {

    @Test
    fun test1() {
        println("a")
    }


    @Test
    fun test2() {
        println("b")
    }

    @Test
    fun test3() {
        println("c")
    }
}