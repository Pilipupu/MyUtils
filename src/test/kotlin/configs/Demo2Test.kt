package configs

import org.junit.jupiter.api.Test

class Demo2Test : ConfigSuiteTest, InitDB() {
    @Test
    fun p() {
        println("2222")
    }
}