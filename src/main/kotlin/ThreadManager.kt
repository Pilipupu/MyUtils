import org.slf4j.MDC
import os.bash.logger
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger


object ThreadManager : ThreadFactory, RejectedExecutionHandler {
    private var pool: ScheduledThreadPoolExecutor
    private var periodicTasks = mutableMapOf<PeriodicTask, ScheduledFuture<*>>()
    private var cancelablePeriodicTasks = mutableMapOf<CancelablePeriodicTask, ScheduledFuture<*>>()
    private var seqNum = AtomicInteger(0)

    init {
        val totalThreadNum = 20
        if (totalThreadNum < 10) {
            logger.warn { "maxThreadNum is configured to $totalThreadNum, which is too small for running zops. Change it to 10 " }
        }
        pool = ScheduledThreadPoolExecutorExt(totalThreadNum, this, this)
        logger.debug {"create ThreadFacade with max thread number: $totalThreadNum"}
    }

    fun destroy() {
        pool.shutdown()
    }
    
    fun submitTask(task: Task): Future<*> {
        return pool.submit {
            task.run()
        }
    }

    fun submitPeriodicTask(task: PeriodicTask, delay: Long = 0): Future<*> {
        val ret = pool.scheduleAtFixedRate({
            try {
                task.run()
            } catch (e: Throwable) {
                val ft = periodicTasks[task]
                if (null != ft) {
                    ft.cancel(true)
                    periodicTasks.remove(task)
                } else {

                }
            }
        }, delay, task.period, task.unit)
        periodicTasks[task] = ret
        return ret
    }

    fun submitCancelablePeriodicTask(task: CancelablePeriodicTask, delay: Long = 0): Future<*> {
        val ret = pool.scheduleAtFixedRate({
            fun cancelTask() {
                val ft = cancelablePeriodicTasks[task]
                if (null != ft) {
                    ft.cancel(true)
                    cancelablePeriodicTasks.remove(task)
                } else {
                    logger.warn {
                        "cannot find feature for task " + task.name +
                                ", the exception happened too soon, will try to cancel the task next time the exception happens"
                    }
                }
            }

            try {
                val cancel = task.run()
                if (cancel) {
                    logger.info { "periodic task ${task.name} will be canceled" }
                    cancelTask()
                }
            } catch (e: Throwable) {
                cancelTask()
            }
        }, delay, task.period, task.unit)
        cancelablePeriodicTasks[task] = ret
        return ret
    }

    override fun newThread(r: Runnable): Thread {
        return Thread(r, "zops-thread-" + seqNum.getAndIncrement())
    }

    override fun rejectedExecution(r: Runnable?, executor: ThreadPoolExecutor?) {
        logger.warn { "Task got rejected by ThreadPool, the pool looks full" }
    }
}

interface PeriodicTask {
    val name: String
    val period: Long
    val unit: TimeUnit

    fun run()
}

interface Task {
    val name: String
    
    fun run()
}

interface CancelablePeriodicTask {
    val name: String
    val period: Long
    var unit: TimeUnit
    
    fun run(): Boolean
}

class ScheduledThreadPoolExecutorExt(
    corePoolSize: Int,
    threadFactory: ThreadFactory?,
    handler: RejectedExecutionHandler?
) : ScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler) {
    override fun beforeExecute(t: Thread?, r: Runnable?) {
        MDC.clear()
    }

    override fun afterExecute(r: Runnable?, t: Throwable?) {
        MDC.clear()
    }
}