package learning.string

import learning.kotlin.A
import org.junit.jupiter.api.Test

class Splitter {
    @Test
    fun demo() {
        println(EnumExample.A.name)
        println(EnumExample.valueOf(EnumExample.A.name))
    }
}

enum class EnumExample(s: String) {
    A("A1")
}
