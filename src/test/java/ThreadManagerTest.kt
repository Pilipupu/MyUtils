import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class ThreadManagerTest {
    @Test
    fun testThreadManager() {
        val list = mutableListOf<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)

        ThreadManager.submitPeriodicTask(object : PeriodicTask {
            override val name: String
                get() = "test"
            override val period: Long
                get() = 2
            override val unit: TimeUnit
                get() = TimeUnit.SECONDS

            override fun run() {
                println(list.size)
            }
        })
        list.remove(1)
        while (true) {
            list.add(9)
            Thread.sleep(1000)
        }
    }
}