package learning.kotlin

import org.junit.jupiter.api.Test

class CobblerDistro(val name: String) {
}


class construction {
    @Test
    fun testConstruction() {
        val c = CobblerDistro("zops")
        c.name
    }

    @Test
    fun test() {
        val a = A().apply {
            func = {
                it.plus("B")
            }
        }
        println(a.func)

    }
}

class A {
    var func:((String) -> String)? = null
}