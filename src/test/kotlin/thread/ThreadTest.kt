package thread

import org.junit.jupiter.api.Test
import java.util.*

class ThreadTest {
    @Test
    fun testThraedArrayList() {
        val list = Collections.synchronizedList(arrayListOf<Int>())

        val runnable = Runnable {
            for (i in 1..1000) {
                synchronized(list) {
                    list.add(i)
                }
            }
            println(Thread.currentThread().name)
        }
        for (i in 1..10) {
            Thread(runnable).start()
        }
        Thread.sleep(1000)
        println(list.size)
    }
}