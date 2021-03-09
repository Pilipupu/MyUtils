package learning.fs

import org.junit.jupiter.api.Test
import java.nio.file.Paths

class PathTest {
    @Test
    fun testPaths() {
        /**
         * Path是关联filesystem的,默认使用的是FileSystems.getDefault()
         */
        println(Paths.get("demo"))
    }
}