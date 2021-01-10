import com.google.common.io.Resources
import java.net.URL
import java.nio.file.FileSystems

var classLoader: ClassLoader = Thread.currentThread().contextClassLoader
val resourceFileSystem = FileSystems.newFileSystem(Resources.getResource("META-INF").toURI(), mapOf<String, String>())

fun getResource(name: String): URL {
    return Resources.getResource(name)
        ?: throw RuntimeException("resource: $name not found")
}

fun hasResource(name: String): Boolean {
    return classLoader.getResource(name) != null
}