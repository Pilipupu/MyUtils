package learning.mockk

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import lang.getPP
import lang.getResource
import lang.hasResource
import org.junit.jupiter.api.Test
import os.network.NetWork
import os.network.NetWork.getEtherDeviceByIP
import os.network.NetWork.isValidIp4
import java.net.NetworkInterface

class MockkTest {
    @Test
    fun mockkObjectTest() {
        mockkObject(NetWork)
        every { getEtherDeviceByIP(any()) } returns NetworkInterface.getNetworkInterfaces().toList()[0]
        println(getEtherDeviceByIP("1"))
    }

    @Test
    fun mockkObjectTest2() {
        mockkObject(NetWork)
        every { isValidIp4("1.1.1.1") } returns false
        println(isValidIp4("1.1.1.1"))
    }

    @Test
    fun mockkStaticTest() {
        mockkStatic("lang.ResourceKt")
        every { getPP(any()) } returns "aaa"

        println(getPP("111"))
    }
}