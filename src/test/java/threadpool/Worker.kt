package threadpool

import java.util.concurrent.Executors

class Worker : Runnable {
    val thread = Executors.defaultThreadFactory().newThread(this)
    var firstTask: Runnable = Runnable {
        println("aaa")
    }
    val tasks = mutableListOf<Runnable>()
    override fun run() {
        runWorker(this)
    }

    private fun runWorker(w: Worker) {
        val wt = Thread.currentThread()
        var task: Runnable?
        //
        do {
            task = getTask()
            if (task == null) {
                break
            }
            task.run()
        } while (true)
        println(wt.name)
    }

    private fun getTask(): Runnable? {
        while (true) {
            //从任务列表中获取task，在某些特殊情况下return null用于结束线程
        }
    }
}