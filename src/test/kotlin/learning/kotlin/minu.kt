package learning.kotlin

import org.junit.jupiter.api.Test

class minu {
    @Test
    fun testMinus() {
        val a = listOf(1, 2, 3)
        val b = listOf(1, 2)

        println(a - b)
        println(b - a)
    }

    @Test
    fun testMinusString() {
        val a = listOf("1", "1", "2").toSet()
        val b = listOf("1", "2")

        println(a - b)
        println(b - a)
        println(a)
    }
}