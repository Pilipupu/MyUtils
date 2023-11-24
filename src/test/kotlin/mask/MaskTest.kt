package mask

import org.junit.jupiter.api.Test
import org.zstack.zops.utils.log.Log

/**
 * Created by IntelliJ IDEA.
 * @Author : jingwang
 * @create 2022/9/19 6:38 PM
 *
 */
class MaskTest {
    @Test
    fun testMask() {
        val log = Log.getLogger(javaClass)
        val u = User("wang", "lass")
        log.info(u.name)
    }
}

data class User(
    val name: String,
    val password: String
)