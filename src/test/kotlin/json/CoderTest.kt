package json

import lang.getResource
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

var aa: String = ""
    get() {
        println("get")
        return a.map["a"]!!
}

object a {
    val map = mutableMapOf<String,String>()
    init {
        println("init")
        map["a"] = "a"
    }
}

class CoderTest {
    @Test
    fun testJsonEncode() {
        val env = CobblerEnvStruct().apply {
            name = "\"compont-bond\""
            tag = "default"
            description = "description"
            path = "path"
            mode = "management"
            iso = "iso"
            state = "Initialize"
            default = false
        }
        val json = env.jsonEncode()
        val e = Coder.jsonDecode(json, CobblerEnvStruct::class)
        println(e.name)
    }

    @Test
    fun testJsonDecode() {
        val ret = """
            {"name":"compute-bond","tag":"default","path":"/var/lib/cobbler/kickstarts/compute-bond.ks","description":"compute env for zstack-node","mode":"compute","iso":"zstack-node","state":"Initialize","default":true}
        """.trimIndent()

        val o = ret.jsonDecode(CobblerEnvStruct::class)
        println(o.name)
    }

    @Test
    fun testJsonDecodeList() {
        getResource("environment/ip-list.json").readText().jsonDecodeAsList(CobblerIPStruct::class)
    }

    @Test
    fun testJsonPrettyPrint() {


        val m = mutableMapOf<String,String>()
        m["a"] = "test"
        println(Coder.jsonPrettyEncode(m))
        println(Coder.jsonEncode(m))
    }

    @Test
    fun testJsonEncode2() {
        val c = CobblerIPStruct()
        println(Coder.jsonEncode(c))
    }

    @Test
    fun testEnumClass() {
        val c = CobblerIPStruct()
        val j = c.jsonEncode()
        val c11 = j.jsonDecode(CobblerIPStruct::class)
        println(c11.type)
    }

    @Test
    fun testJson() {
        val a = JSONTest()
        println(a.jsonEncode().jsonDecode(JSONTest::class).a)
    }

    @Test
    fun testObjectInit() {
        println(aa)
        println(aa)
    }

    @Test
    fun testSizeUnit() {
        println(SizeUtils.sizeStringToBytes("1.745 TB"))
        println("1.745".toDouble())
    }

    @Test
    fun testClass() {
        val c = String::class
        println(c.simpleName)
        println(c.qualifiedName)
        if (String::class.isSuperclassOf(c)) {
            println("true")
        }
    }
}

class CobblerIPStruct {
    var sequence: Int = 0
    var ips = listOf<String>()
    var state: String = "initialize"
    var tag: String = "default"
    val testParam = ""
    val type = PhysicalDeviceType.SSD
}

class JSONTest() {
    var a = """a""""
}

enum class PhysicalDeviceType {
    HDD,
    SSD;

    companion object {
        fun getType(type: String) : PhysicalDeviceType {
            return values().first {
                it.name in type.toUpperCase()
            }
        }
    }
}

enum class ControllerMode() {
    HBA,
    RAID,
    Mixed;

    fun string() : String {
        return this.name
    }
    fun getArcconfValue() : Int {
        return when(this) {
            HBA -> 2
            RAID -> 3
            Mixed -> 5
        }
    }

    companion object {
        private val VALUES = values()
        fun getByValue(value: String) = VALUES.firstOrNull { it.string() == value }
    }
}

