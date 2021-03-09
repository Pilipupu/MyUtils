package configs

import com.google.common.io.Resources
import com.google.common.io.Resources.getResource
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.io.path.Path

class SystemConfigTest {
    @Test
    fun getSystemProperties() {
        println(System.getProperty("user.home"))
        println(Paths.get("/test").resolve("demo"))
        println(Paths.get("test").resolve("demo").toAbsolutePath())
        println(Paths.get("/test").resolve("/demo"))
        val p = Paths.get(System.getProperty("user.home")).resolve(".zops").toAbsolutePath().toString()
        var get = Paths.get(".")
        println(get)
        println(get.toAbsolutePath())
    }

    @Test
    fun loadSystemProperties() {
        System.setProperty("demo.funcPlus.a","123")
        System.setProperty("ZOPS_COBBLER_NETWORK_IP","123")
        System.getProperties().load(
            getResource("config.properties").openStream()
        )
        println(System.getProperty("ZOPS_COBBLER_NETWORK_IP"))
        println(System.getProperty("demo.funcPlus.a"))
    }
}