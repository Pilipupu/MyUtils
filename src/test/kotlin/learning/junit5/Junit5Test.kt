package learning.junit5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Junit5Test : SuiteTest(){

    @Test
    fun testBeforeAll() {
        println("test")
    }

    @Test
    fun testRunAgain() {
        println("test")
    }
}

class Case1Test : SuiteTest() {
    @Test
    fun testBeforeAll() {
        println("case1")
    }
}