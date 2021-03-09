package learning.property

import org.junit.jupiter.api.Test

class PropertiesTest {
    @Test
    fun getPropertyTest() {
        println(System.getProperty("java.io.tmpdir"))
    }
}