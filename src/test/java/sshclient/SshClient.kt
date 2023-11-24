package sshclient

import json.jsonEncode
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.connection.channel.direct.Session
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import org.junit.jupiter.api.Test
import org.zstack.zops.utils.log.logger
import java.io.IOException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


class SshClient {
//    val ssh = SSH("172.30.3.151", "cube_password")
    val ssh = SSH("172.25.200.207", "password")

    @Test
    fun testSSH() {
        val ret = ssh.exec("""sas3ircu list""")
        val r = Regex(" *\\d+.*")
        ret.stdOut.lineSequence().forEach {
            if (r.matches(it)) {
                it.split(Regex(" +")).forEach {
                    println(it)
                }
            }
        }
    }

    @Test
    fun getPD() {
        val ret = ssh.exec("sas3ircu 0 display")
        val lst = ret.stdOut.split(Regex("Device is a Hard disk|Device is a Enclosure services device")).map { it.trim() }
        val regexs = mutableListOf<Regex>().apply {
            add(".*(Enclosure) # *: (\\d+)")
            add(".*(Slot) # *: (\\d+)")
            add(".*(SAS Address) *: (.*)")
            add(".*(State).*: (.*)\\n")
            add(".*(Size).*: (.*)/.*\\n")
            add(".*(Manufacturer).*: (.*)\\n")
            add(".*(Model Number).*: (.*)\\n")
            add(".*(Firmware Revision).*: (.*)\\n")
            add(".*(Serial No).*: (.*)\\n")
            add(".*(Unit Serial No)\\(VPD\\).*: (.*)\\n")
            add(".*(GUID).*: (.*)")
        }
        lst.forEach { s->
            s.lineSequence().forEach {
                val r = regexs.find { r ->
                    val result = r.find(it)
                    val b = result != null
                    if (b) {
                        println(result!!.destructured.component2())
                    }
                    b
                }
            }
        }
    }

    @Test
    fun getPDInfo() {
        val t0 = System.currentTimeMillis()
        val s = """Enclosure #                             : 2
  Slot #                                  : 9
  SAS Address                             : 56c92bf-0-01c5-88e9
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 457862/937703087
  Manufacturer                            : ATA     
  Model Number                            : INTEL SSDSC2KG48
  Firmware Revision                       : 0120
  Serial No                               : PHYG102000E9480BGN
  Unit Serial No(VPD)                     : PHYG102000E9480BGN
  GUID                                    : 55cd2e415326b71c
  Protocol                                : SATA
  Drive Type                              : SATA_SSD"""
        val r0 = s.replace(Regex(":.*"), "(:.*)\\\\\\\\n")
        val r1 = Regex(r0)
        println(r1)
        val r = Regex(".*Enclosure # *: (\\d+)\\n" +
                ".*Slot # *: (\\d)\\n" +
                ".*SAS Address *: (.*)\\n" +
                ".*State.*: (.*)\\n" +
                ".*Size.*: (.*)/.*\\n" +
                ".*Manufacturer.*: (.*)\\n" +
                ".*Model Number.*: (.*)\\n" +
                ".*Firmware Revision.*: (.*)\\n" +
                ".*Serial No.*: (.*)\\n" +
                ".*Unit Serial No\\(VPD\\).*: (.*)\\n" +
                ".*GUID.*: (.*)")
        val a = r1.find(s)!!
        a.destructured.toList().forEach {
            println(it)
        }
        println(System.currentTimeMillis() - t0)
        val m = mutableMapOf<String, (String)->Unit>()
        m["a"]!!("b")
    }

    @Test
    fun testMoreRegex() {
        val regexs = mutableListOf<Regex>().apply {
            add(".*Enclosure # *: (\\d+)")
            add(".*Slot # *: (\\d+)")
            add(".*SAS Address *: (.*)")
            add(".*State.*: (.*)\\n")
            add(".*Size.*: (.*)/.*\\n")
            add(".*Manufacturer.*: (.*)\\n")
            add(".*Model Number.*: (.*)\\n")
            add(".*Firmware Revision.*: (.*)\\n")
            add(".*Serial No.*: (.*)\\n")
            add(".*Unit Serial No\\(VPD\\).*: (.*)\\n")
            add(".*GUID.*: (.*)")
        }

        val t0 = System.currentTimeMillis()
        val s = """Enclosure #                             : 2
  Slot #                                  : 9
  SAS Address                             : 56c92bf-0-01c5-88e9
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 457862/937703087
  Manufacturer                            : ATA     
  Model Number                            : INTEL SSDSC2KG48
  Firmware Revision                       : 0120
  Serial No                               : PHYG102000E9480BGN
  Unit Serial No(VPD)                     : PHYG102000E9480BGN
  GUID                                    : 55cd2e415326b71c
  Protocol                                : SATA
  Drive Type                              : SATA_SSD"""
        s.lineSequence().forEach {
            val r = regexs.find { r ->
                val result = r.find(it)
                val b = result != null
                if (b) {
                    println(result!!.destructured.component1())
                }
                b
            }
        }
        println(System.currentTimeMillis() - t0)
    }


    @Test
    fun getLD() {
        val ret = ssh.exec("sas3ircu 0 display")
        val lst = ret.stdOut.split(Regex("IR volume \\d+")).map { it.trim() }
        println("1")
    }

    @Test
    fun getArPD() {
        val ret = ssh.exec("arcconf getconfig 1 pd").stdOut
        ret.split(Regex("Device #\\d+")).forEach {
            if (it.findValue("Device is an Enclosure Services Device") != null) {
                return@forEach
            }
            val (channelID, deviceID) = it.findValue("Reported Channel,Device\\(T:L\\) *: (\\d+),(\\d+)")?.destructured ?: return@forEach
            println("$channelID: $deviceID")
        }
    }

    @Test
    fun testTTT() {
        println(ssh.exec("python /root/test.py").stdOut)
    }

    @Test
    fun testGetPD() {
        val s = "        Reported Location                  : Enclosure 1, Slot 0(Connector 0:CN0)"
        val r = Regex("Reported Location *: (Enclosure \\d+, Slot \\d+)")
        val o = r.find(s)
        println(o!!.destructured.component1())
    }

    @Test
    fun testGetArPD() {
        val ret = ssh.exec("arcconf getconfig 1 pd").stdOut
        ret.lineSequence().forEach { line ->
            pdRegexes.forEach inner@ { r ->
                val result = r.find(line) ?: return@inner
                val (key,value) = result.destructured
                println("$key: $value")
            }
        }
    }

    @Test
    fun testRegex() {
        val s = "(Device is a Hard disk|Device is a Enclosure services device)"
        val r = Regex(s)
        var input = """Device is a Enclosure services device
  Enclosure #                             : 2
  Slot #                                  : 16
  SAS Address                             : 56c92bf-0-01c5-88fe
  State                                   : Standby (SBY)
  Manufacturer                            : PMCSIERA
  Model Number                            : SXP 24Sx12G
  Firmware Revision                       : B128
  Serial No                               : PMCSIERAC
  Unit Serial No(VPD)                     : 56c92bf001c588fe
  GUID                                    : N/A
  Protocol                                : SAS
  Device Type                             : Enclosure services device
------------------------------------------------------------------------
Enclosure information
------------------------------------------------------------------------
  Enclosure#                              : 1
  Logical ID                              : 56c92bf0:005e5d30
  Numslots                                : 8
  StartSlot                               : 0
  Enclosure#                              : 2
  Logical ID                              : 56c92bf0:01c5891c
  Numslots                                : 17
  StartSlot                               : 0
------------------------------------------------------------------------
SAS3IRCU: Command DISPLAY Completed Successfully.
SAS3IRCU: Utility Completed Successfully."""
        val p = Pattern.compile(s)
        val m = p.matcher(input)
        println(m.find())
        println(m.group(1))
        var input1 = """Device is a Hard disk
  Enclosure #                             : 2
  Slot #                                  : 13
  SAS Address                             : 56c92bf-0-01c5-88ed
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 457862/937703087
  Manufacturer                            : ATA
  Model Number                            : INTEL SSDSC2KG48
  Firmware Revision                       : 0120
  Serial No                               : PHYG102000GB480BGN
  Unit Serial No(VPD)                     : PHYG102000GB480BGN
  GUID                                    : 55cd2e415326b762
  Protocol                                : SATA
  Drive Type                              : SATA_SSD"""
        val m1 = p.matcher(input1)
        println(m1.find())
        println(m1.group(1))
    }

    fun convertToSinglePhsyicalDeviceInfo(input: String) : Map<Int, String> {
        val p = Pattern.compile("Device #(\\d+)")
        val matcher = p.matcher(input)
        val map = HashMap<Int, String>()
        var lastStart = 0
        while (true) {
            if (lastStart == 0) {
                matcher.find()
            }
            lastStart = matcher.end()
            val id = matcher.group(1).toInt()
            if (matcher.find()) {
                map[id] = input.substring(lastStart, matcher.start())
            } else {
                map[id] = input.substring(lastStart, input.length)
                break
            }
        }
        return map
    }
}

private fun MutableList<Regex>.add(element: String) {
    add(Regex(element))
}


class SSH(var ip: String, var privateKeyPath: String) {
    private val ssh = SSHClient().apply {
        this.addHostKeyVerifier(PromiscuousVerifier())
        this.connectTimeout = 3000
        this.connect(ip)
        this.authPassword("root", privateKeyPath)
    }
    private val session: Session = ssh.startSession()


    fun exec(cmd: String) : Result {
        logger.debug("[ssh exec] $ip: $cmd")
        val rawResult = session.exec(cmd)
        if (!cmd.contains("&")) {
            rawResult.join(3, TimeUnit.SECONDS)
        }
        val result = Result(
            returnCode = rawResult.exitStatus,
            stdOut = String(rawResult.inputStream.readAllBytes()),
            stdErr = String(rawResult.errorStream.readAllBytes()),
            cmd = cmd,
            ip = ip
        )
        logger.debug {
            val s = "[ssh result] $ip: $cmd \n"
            if (result.isSuccess()) {
                return@debug s+result.stdOut
            } else {
                return@debug s+result.stdOut+result.stdErr
            }
        }
        session.close()
        return result
    }

    fun clean() {
        ssh.close()
    }
}

class Result(var returnCode: Int, var stdOut: String, var stdErr: String,var cmd: String,var ip: String) {
    init {
        if (!isSuccess()) {
            throw SshRemoteExecutionException("[$ip] run $cmd failed, error msg: $stdOut $stdErr ")
        }
    }

    fun isSuccess() : Boolean {
        return returnCode == 0
    }
}

fun String.findValue(r: Regex): MatchResult? {
    return r.find(this)
}

fun String.findValue(r: String): MatchResult? {
    return Regex(r).find(this)
}

fun String.findValues(r: String): Sequence<MatchResult> {
    return Regex(r).findAll(this)
}

fun String.findValues(r: Regex): Sequence<MatchResult> {
    return r.findAll(this)
}

class SshRemoteExecutionException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}

class MySession(val session: Any) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        val clazz = Class.forName("net.schmizz.sshj.connection.channel.direct.SessionChannel")
        val fs = clazz.declaredFields
        val f = fs.find {
            it.trySetAccessible()
            it.name == "usedUp"
        }!!
        f.set(session, true)
        return method!!.invoke(session, args)
    }
}

class MyProxy(val session: Session) {
    fun getProxy() : Session {
        val classLoader = session.javaClass.classLoader
        val interfaces = session.javaClass.interfaces
        val mySession = MySession(session)
        return Proxy.newProxyInstance(classLoader, interfaces, mySession) as Session
    }
}

class SessionProxy(val session: Session) : Session by session {
    override fun exec(command: String?): Session.Command {
        val clazz = Class.forName("net.schmizz.sshj.connection.channel.direct.SessionChannel")
        val fs = clazz.declaredFields
        val f = fs.find {
            it.trySetAccessible()
            it.name == "usedUp"
        }!!
        f.set(session, false)
        return session.exec(command)
    }
}

private val pdRegexes = mutableListOf<Regex>().apply {
    addRaidRegex("(Reported Location) *: (Enclosure \\d+, Slot \\d+)")
    addRaidRegex("(State) *: (.*)")
    addRaidRegex("(Vendor) *: (.*)")
    addRaidRegex("(Model) *: (.*)")
    addRaidRegex("(Serial number) *: (.*)")
    addRaidRegex("(World-wide name) *: (.*)")
    addRaidRegex("(Total Size) *: (.*)")
    addRaidRegex("(SSD) *: (.*)")
    addRaidRegex("(Transfer Speed) *: (.*)")
}


fun MutableList<Regex>.addRaidRegex(element: String) {
    add(Regex(element))
}

enum class PhysicalDeviceType {
    HDD,
    SSD,
    UNKNOWN;

    companion object {
        fun getType(type: String) : PhysicalDeviceType {
            return values().firstOrNull {
                it.name in type.toUpperCase()
            } ?: UNKNOWN
        }
    }
}
class EnumTypeTest {
    var str = "11"

    init {
//        println(str)
    }

    @Test
    fun testEnum() {
        println(PhysicalDeviceType.getType("SATA_SSD").name)
    }

    @Test
    fun testType() {
        val r = Regex(".*(Drive Type) : (.*)")
        val s = "  Drive Type                              : SATA_SSD"
        println(r.find(s)!!.destructured.component2())
    }

    @Test
    fun testThrowss() {
        testThrows()
    }


    @Test
    fun testStorCLI64() {
        val j = """
            "Controllers":[
            {
            	"Command Status" : {
            		"CLI Version" : "007.1912.0000.0000 Nov 23, 2021",
            		"Operating system" : "Linux 3.10.0-693.el7.x86_64",
            		"Controller" : 0,
            		"Status" : "Success",
            		"Description" : "None"
            	},
            	"Response Data" : {
            		"/c0/v0" : [
            			{
            				"DG/VD" : "0/0",
            				"TYPE" : "RAID1",
            				"State" : "Optl",
            				"Access" : "RW",
            				"Consist" : "No",
            				"Cache" : "RWTD",
            				"Cac" : "-",
            				"sCC" : "ON",
            				"Size" : "1.745 TB",
            				"Name" : ""
            			}
            		],
            		"PDs for VD 0" : [
            			{
            				"EID:Slt" : "8:6",
            				"DID" : 9,
            				"State" : "Onln",
            				"DG" : 0,
            				"Size" : "1.745 TB",
            				"Intf" : "SATA",
            				"Med" : "SSD",
            				"SED" : "N",
            				"PI" : "N",
            				"SeSz" : "512B",
            				"Model" : "INTEL SSDSC2KG019T8",
            				"Sp" : "U",
            				"Type" : "-"
            			},
            			{
            				"EID:Slt" : "8:7",
            				"DID" : 10,
            				"State" : "Onln",
            				"DG" : 0,
            				"Size" : "1.745 TB",
            				"Intf" : "SATA",
            				"Med" : "SSD",
            				"SED" : "N",
            				"PI" : "N",
            				"SeSz" : "512B",
            				"Model" : "INTEL SSDSC2KG019T8",
            				"Sp" : "U",
            				"Type" : "-"
            			}
            		],
            		"VD0 Properties" : {
            			"Strip Size" : "256 KB",
            			"Number of Blocks" : 3749642240,
            			"VD has Emulated PD" : "Yes",
            			"Span Depth" : 1,
            			"Number of Drives Per Span" : 2,
            			"Write Cache(initial setting)" : "WriteBack",
            			"Disk Cache Policy" : "Disk's Default",
            			"Encryption" : "None",
            			"Data Protection" : "Disabled",
            			"Active Operations" : "None",
            			"Exposed to OS" : "Yes",
            			"OS Drive Name" : "/dev/sda",
            			"Creation Date" : "28-02-2022",
            			"Creation Time" : "02:10:45 AM",
            			"Emulation type" : "default",
            			"Cachebypass size" : "Cachebypass-64k",
            			"Cachebypass Mode" : "Cachebypass Intelligent",
            			"Is LD Ready for OS Requests" : "Yes",
            			"SCSI NAA Id" : "600605b010cbe11029aeeba55d3ea3e6",
            			"Unmap Enabled" : "N/A"
            		}
            	}
            }
            ]
            }
        """.trimIndent()
        val r = Regex("/c0/v(\\d+)")
        val m = r.findAll(j)
        m.forEach {
            println(it.destructured.component1())
            println(it.value)
            println(it.range)
        }
    }

    @Test
    fun testJsonCoder() {
        val t = TestClass()
        println(t.jsonEncode())
    }

    @Test
    fun testEEnum() {
        val a = listOf<String>("1","2")
        val b = listOf<String>("1","2")
        println(a == b)
    }
}

fun testTT() {
    testThrows()
}

fun testThrows() {
    throw IOException("aaa")
}

class TestClass() {
    var param1: String? = null
    var param2 = "aaa"
}

enum class RaidCmd(val cmd: String) {
    ARRCONF("arcconf"),
    SAS3IRCU("sas3ircu"),
    STORCLI64("storcli64")
}