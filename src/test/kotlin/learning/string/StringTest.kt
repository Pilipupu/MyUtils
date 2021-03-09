package learning.string

import org.junit.jupiter.api.Test

class StringTest {
    @Test
    fun testStringPlus() {
        val DISTRO_SUFFIX = "-x86_64"
        val name = "zstack-node"
        println("$name$DISTRO_SUFFIX")
    }

    @Test
    fun testCobblerCommandMatch() {
        var importCommand = "cobbler import --name=zstack-node --path=/root/iso"
        val s = importCommand.split(" ")

        val removeDistroCommand = "cobbler distro remove --name=zstack"
    }

    @Test
    fun testJoin() {
        val list = listOf<String>("zstack", "zops")
        println(list.joinToString("\n"))

        class CobblerDistro(var name: String)

        val list2 = listOf<CobblerDistro>(CobblerDistro("zstack"), CobblerDistro("zops"))
        list2.joinToString("\n") { it.name }
    }

    @Test
    fun testIsCommandMatch() {
        println(isCommandMatch("cobbler import *", "cobbler import"))
    }
}

fun isCommandMatch(pattern: String, cmd: String): Boolean {
    val re = Regex(pattern=pattern)
    return re.containsMatchIn(cmd)
}