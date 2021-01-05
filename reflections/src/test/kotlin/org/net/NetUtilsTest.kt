package org.net

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NetUtilsTest {
    @Test
    fun isValidIp4Test() {
        assert(NetUtils.isValidIp4("192.168.0.1")) { "true" }
        assertTrue { NetUtils.isValidIp4("192.168.0.1") }
        assertFalse { NetUtils.isValidIp4("192") }
    }
}