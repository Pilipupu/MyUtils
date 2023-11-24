package raid

import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.junit.jupiter.api.Test
import java.util.*

class RaidTest {
    @Test
    fun testRaidMode() {
        println(UUID.nameUUIDFromBytes("123".toByteArray()))
        val c = Controlller()
        c.sn = "123"
        println(c.uuid)
        println(c.sn)
        println(c.a)
        c.sn = "babsabd"
        println(c.a)
    }

    @Test
    fun testString() {
        val a = """sda\n"""
        println(a)
        println(a.replace("""\n""", ""))
    }
}

class Controlller {
    lateinit var uuid: String
    var sn  = "123"
        set(value) {
            uuid = UUID.nameUUIDFromBytes(value.toByteArray()).toString()
            field = value
        }

    var a = "1234"
    get() {
        return sn
    }
}


enum class RaidControllerMode {
    UNKNOWN,
    HBA,
    RAID,
    MIXED;

    fun string() : String {
        return this.name
    }
    fun getArcconfValue() : Int {
        return when(this) {
            HBA -> 2
            RAID -> 3
            MIXED -> 5
            UNKNOWN -> throw IllegalArgumentException("unknow raid controller mode, arcconf could not change to this mode")
        }
    }

    companion object {
        private val VALUES = values()
        fun getByName(value: String) = VALUES.first { it.string() == value.toUpperCase() }
    }
}