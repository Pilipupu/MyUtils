package learning.kotlin

import org.junit.jupiter.api.Test

class list {
    @Test
    fun testMutableList() {
        val a = emptyList<String>()
        println(a)
    }

    @Test
    fun testFilter() {
        val a = listOf(1,2,3,4,5)
    }

    @Test
    fun testAddWithIndex() {
        val a = mutableListOf<Int>()
        a.add(0,1)
        a.add(0,2)
        println(a)
    }

    @Test
    fun testSet() {

    }
}