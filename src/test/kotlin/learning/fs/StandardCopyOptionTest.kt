package learning.fs

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class StandardCopyOptionTest {
    @Test
    fun testReplace_Existing() {
        val p1 = Paths.get("demo")
        val p2 = Paths.get("demo1")
        assertThrows<Exception> { Files.copy(p1, p2) }
        assertDoesNotThrow { Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING) }
    }
}