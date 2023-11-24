package demo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.nio.file.Files
import java.nio.file.Paths


class ExampleCase {
    @Test
    @ExtendWith(IgnoreIOExceptionExtension::class)
    fun test() {
        val func: () -> String = {
            "demo.funcPlus.a"
        }
        assert("demo.funcPlus.a" != func())
    }
    fun hll(cmd: String) : String {
        println(cmd)
        return  cmd
    }
    fun printFun(s: String, fn: (cmd: String) ->String) {
        fn(s)
    }
    @Test
    fun test2() {
        printFun("demo.funcPlus.a", ::hll)
    }


    @Test
    fun readFile() {
        println(Files.readString(Paths.get("src/test/resources/test.json")))

    }

    @Test
    fun testComputeIfAbsent() {
        val m = mutableMapOf<String, String>()
        m.computeIfAbsent("a") {
            println(it)
            it
        }
        println(m["a"])
    }

    @Test
    fun testRegex() {
        val regex = createRegexFromGlob("/var/test/out".replace("\\{.*\\}".toRegex(), ".*"))
    }

    fun createRegexFromGlob(glob: String): String? {
        var out: String? = "^"
        for (i in 0 until glob.length) {
            val c = glob[i]
            when (c) {
                '*' -> out += ".*"
                '?' -> out += '.'
                '\\' -> out += "\\\\"
                else -> out += c
            }
        }
        out += '$'
        return out
    }
}

object BashMock {
    var run: ((cmd: String) -> String)? = null
}