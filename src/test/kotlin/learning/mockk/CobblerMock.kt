package learning.mockk

import org.junit.jupiter.api.Test
import os.bash.Bash
import os.bash.BashReturn

class CobblerSpec {
    val cobblerSystems = mutableListOf<CobblerSystem>()
    val cobblerProfiles = mutableListOf<CobblerProfile>()
    val cobblerDistros = mutableListOf<CobblerDistro>()
}

class CobblerSystem(var name: String)
class CobblerProfile(var name: String)
class CobblerDistro(var name: String)

class CobblerMockTest {

    @Test
    fun mockCobblerRun() {
        val import = "cobbler import --name=zstack --path=/root/iso"
        parseCommand(import).forEach {
            println(it.key.plus(":").plus(it.value))
        }

        val c = CobblerSpec()
        c.mockCobbler(import)
        c.cobblerDistros.forEach {
            println(it.name)
        }
    }

    @Test
    fun mockListDistros() {
        val import = "cobbler import --name=zstack --path=/root/iso"

        val listDistro = "cobbler distro list"
        val c = CobblerSpec()
        c.mockCobbler(import)
        println(c.mockCobbler(listDistro).stdout)
    }
}

fun CobblerSpec.mockCobbler(command: String): BashReturn {
    val m = parseCommand(command)

    when {
        isCommandMatch(CobblerCommand.COBBLER_IMPORT, command) -> {
            addCobblerDistro(m)
            addCobblerProfile(m);
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_PROFILE_ADD, command) -> {
            addCobblerProfile(m);
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_PROFILE_LIST, command) -> {
            val stout = listCobblerProfiles()
            return BashReturn(retCode = 0, stdout = stout, stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_PROFILE_REMOVE, command) -> {
            removeCobblerProfile(m);
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_PROFILE_RENAME, command) -> {
            renameCobblerProfile(m)
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_SYSTEM_ADD, command) -> {
            addCobblerSystem(m)
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_SYSTEM_REMOVE, command) -> {
            removeCobblerSystem(m)
            return BashReturn(retCode = 0, stdout = "", stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_SYSTEM_LIST, command) -> {
            val stout = listCobblerSystems()
            return BashReturn(retCode = 0, stdout = stout, stderr = "", command = command)
        }
        isCommandMatch(CobblerCommand.COBBLER_DISTRO_LIST, command) -> {
            val stout = listCobblerDistros()
            return BashReturn(retCode = 0, stdout = stout, stderr = "", command = command)
        }

    }
    return Bash(command).execute()
}

fun parseCommand(command: String): Map<String, String> {
    val m = mutableMapOf<String, String>()

    var s = command.split(" ")
    s = s.subList(2, s.size)
    s.forEach {
        val kv = it.split("=")
        if (kv.size == 2) {
            m[kv[0]] = kv[1]
        }
    }
    return m;
}

fun isCommandMatch(pattern: String, cmd: String): Boolean {
    return Regex(pattern = pattern).containsMatchIn(cmd)
}

fun CobblerSpec.addCobblerProfile(m: Map<String, String>) {
    m["--name"]?.let { CobblerProfile(it) }?.let { cobblerProfiles.add(it) }
}

fun CobblerSpec.addCobblerDistro(m: Map<String, String>) {
    m["--name"]?.let { CobblerDistro(it) }?.let { cobblerDistros.add(it) }
}

fun CobblerSpec.listCobblerProfiles(): String {
    val stout = StringBuilder()
    cobblerProfiles.forEach {
        stout.append(it.name).append("\n")
    }
    return stout.toString()
}

fun CobblerSpec.listCobblerDistros(): String {
    val stout = StringBuilder()
    cobblerDistros.forEach {
        stout.append(it.name).append("\n")
    }
    return stout.toString()
}

fun CobblerSpec.removeCobblerProfile(m: Map<String, String>) {
    cobblerProfiles.removeIf {
        it.name == m["--name"]
    }
}

fun CobblerSpec.renameCobblerProfile(m: Map<String, String>) {
    cobblerProfiles.find {
        it.name == m["--name"]
    }.apply {
        this?.name = m["--newname"] ?: error("")
    }
}

fun CobblerSpec.addCobblerSystem(m: Map<String, String>) {
    m["--name"]?.let { CobblerSystem(it) }?.let { cobblerSystems.add(it) }
}

fun CobblerSpec.removeCobblerSystem(m: Map<String, String>) {
    cobblerSystems.removeIf {
        it.name == m["--name"]
    }
}

fun CobblerSpec.listCobblerSystems(): String {
    val stout = StringBuilder()
    cobblerSystems.forEach {
        stout.append(it.name).append("\n")
    }
    return stout.toString()
}


object CobblerCommand {
    const val COBBLER_IMPORT = "cobbler import *"
    const val COBBLER_SYSTEM_ADD = "cobbler system add *"
    const val COBBLER_SYSTEM_REMOVE = "cobbler system remove *"
    const val COBBLER_SYSTEM_EDIT = "cobbler system edit *"
    const val COBBLER_SYSTEM_LIST = "cobbler system list"
    const val COBBLER_PROFILE_ADD = "cobbler profile add *"
    const val COBBLER_PROFILE_RENAME = "cobbler system rename *"
    const val COBBLER_PROFILE_REMOVE = "cobbler profile remove *"
    const val COBBLER_PROFILE_LIST = "cobbler profile list"
    const val COBBLER_DISTRO_LIST = "cobbler distro list"
}