package log

import org.junit.jupiter.api.Test
import org.zstack.zops.utils.log.Log

class TestLog {
    @Test
    fun testLog() {
        val logger = Log.getLogger(this::class.java)
        logger.debug("aaaa")
    }
}