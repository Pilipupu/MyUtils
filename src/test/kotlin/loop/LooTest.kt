package loop

import lang.getResource
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

enum class CobblerHostState(name: String) {
    Discovered("Discovered"),
    Installing("Installing"),
    Done("Done"),
    Failed("Failed")
}

class LooTest {
    @Test
    fun testLoop() {

        while (doProbe()) {

        }
    }

    fun doProbe(): Boolean {
        var path = Path.of(getResource("env/demo").toURI())
        var lines = Files.readAllLines(path)
        if (lines[0] == "ok") {
            return false
        }
        println(lines[0])
        Thread.sleep(3000)
        return true
    }
}