package configs

import org.junit.jupiter.api.Test

class Demo1Test : ConfigSuiteTest, InitDB() {
    @Test
    fun p() {
        println("1111")
    }
}