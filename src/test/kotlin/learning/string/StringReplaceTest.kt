package learning.string

import org.junit.jupiter.api.Test

class StringReplaceTest {
    @Test
    fun stringReplaceTest() {
        val s = "192.0.0.0"
        println(s.replace(".", "-"))
    }
}