package net
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import os.network.NetWork
import os.network.NetWork.getEtherDeviceByIP
import os.network.getEtherDeviceByIP1
import java.net.NetworkInterface
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NetUtilsTest {
    @Test
    fun isValidIp4Test() {
        assert(NetWork.isValidIp4("192.168.0.1")) { "true" }
        assertTrue { NetWork.isValidIp4("192.168.0.1") }
        assertFalse { NetWork.isValidIp4("192") }
    }

    @Test
    fun mockNetworkInObj() {
        mockkObject(NetWork)
        every { NetWork.getEtherDeviceByIP(any()) } returns NetworkInterface.getNetworkInterfaces().toList()[0]
        assertEquals(NetworkInterface.getNetworkInterfaces().toList()[0], getEtherDeviceByIP("1"))
    }

    @Test
    fun mockNetworkI() {
        mockkStatic("org.net.NetWorkKt")
        every { getEtherDeviceByIP1(any()) } returns NetworkInterface.getNetworkInterfaces().toList()[0]
        println(getEtherDeviceByIP1("1"))
        assertEquals(NetworkInterface.getNetworkInterfaces().toList()[0], getEtherDeviceByIP1("1"))
    }
}