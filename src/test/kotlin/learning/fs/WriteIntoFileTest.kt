package learning.fs

import org.junit.jupiter.api.Test
import java.net.URI

class WriteIntoFileTest {
    @Test
    fun testWriteIntoURI() {
        URI("demo").toURL().readText()
    }
}