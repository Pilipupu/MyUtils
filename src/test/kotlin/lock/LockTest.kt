package lock

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import net.schmizz.sshj.common.LoggerFactory
import net.schmizz.sshj.common.StreamCopier
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Created by IntelliJ IDEA.
 * @Author : jingwang
 * @create 2022/7/22 10:32 AM
 *
 */
class LockTest {
    val l = ReentrantLock()
    val c = l.newCondition()
    val countDown = CountDownLatch(1)

    @Test
    fun testLock() {
        val t1 = thread {
            start()
        }
        val t2 = thread {
            start()
        }
        t1.join()
        t2.join()
    }

    fun start() {
        if (!l.tryLock()) {
            println("wait " + Thread.currentThread().name)
            countDown.await()
            println("release")
        } else {
            println("get lock " + Thread.currentThread().name)
            Thread.sleep(1000)
            countDown.countDown()
            println(countDown.count)
            l.unlock()
        }
    }

    @Test
    fun testListFiles() {
        val findDirName = "zops"

    }

    @Test
    fun testRunBlock() {
        runBlocking {
            async {  }
        }
    }

    @Test
    fun testSystemIn() {
        val i = System.`in`
        //
//                new StreamCopier(shell.getErrorStream(), System.err, LoggerFactory.DEFAULT)
//                        .bufSize(shell.getLocalMaxPacketSize())
//                        .spawn("stderr");

        // Now make System.in act as stdin. To exit, hit Ctrl+D (since that results in an EOF on System.in)
        // This is kinda messy because java only allows console input after you hit return
        // But this is just an example... a GUI app could implement a proper PTY
        val o = System.out
        StreamCopier(i, o, LoggerFactory.DEFAULT)
            .bufSize(1024)
            .copy()
    }
}