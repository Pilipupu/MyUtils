package json

import lang.getResource
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class CoderTest {
    @Test
    fun testJsonEncode() {
        val env = CobblerEnvStruct().apply {
            name = "compont-bond"
            tag = "default"
            description = "description"
            path = "path"
            mode = "management"
            iso = "iso"
            state = "Initialize"
            default = false
        }
        println(env.jsonEncode())
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
    }
}

class CobblerIPStruct {
    var sequence: Int = 0
    var ips = listOf<String>()
    var state: String = "initialize"
    var tag: String = "default"
}