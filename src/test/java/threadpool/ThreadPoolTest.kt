package threadpool

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread


class ThreadPoolTest {
    var executorService = Executors.newFixedThreadPool(10)

    @Test
    fun testThread() {
        for (i in 0..9) {
            executorService.submit {
                println(Thread.currentThread().name)
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
//        executorService.shutdown()
        println("Waiting...")
        val isTermination = executorService.awaitTermination(3, TimeUnit.SECONDS)
        println("Waiting...Done")
        if (isTermination) {
            println("All Thread Done")
        }
        println(Thread.currentThread().name)
    }

    @Test
    fun testCountDown() {
        val countDownLatch = CountDownLatch(3)
        val count = AtomicInteger(0)

        val executors = Executors.newFixedThreadPool(10)
        for (i in 0 until 2) {
            executors.execute {
                println(i)
                countDownLatch.countDown()
                count.getAndAdd(1)
            }
        }
        countDownLatch.await()
        println(count.get())

    }

    @Test
    fun testRunBlocking() {
        for (n in 0 until 10) {
            println("nnnn")
            runBlocking {
                for (i in 0 until 10) {
                    launch {
                        println(i)
                    }
                }
                println("aaaaaa")
            }
        }
    }

    @Test
    fun testCreateOneThousandThread() {
        val start = System.currentTimeMillis()
        for (i in 0 until 1000) {
            thread {
                println("aa")
            }
        }
        println(System.currentTimeMillis() - start)
    }

    @Test
    fun testWorkerRun() {
        val w = Worker()
        w.firstTask = Runnable {
            println("aaa")
        }
        w.thread.start()
    }
}