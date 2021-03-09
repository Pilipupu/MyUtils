package lang

import org.junit.jupiter.api.Test
import java.nio.file.Paths

class resource {
    @Test
    fun test() {
        println(getResource("env/demo").readText())
        println(hasResource("demo"))
    }

    @Test
    fun testPaths() {
        println(Paths.get("demo", "/aaa"))
    }
}