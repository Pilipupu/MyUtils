package learning.process

import org.junit.jupiter.api.Test

class ProcessTest {
    @Test
    fun testProcessBuild() {
        val p = Runtime.getRuntime().exec("echo hello")
        val instream = p.inputStream.bufferedReader()

//        println(instream.readLine())
        /**
         *  1. inputstream里面的内容只能被消费一遍，消费过就没了
         *  2. 流能转换成Stream，实际上调用的readline()
         */
        instream.lines().forEach { println(it) }
    }

    @Test
    fun testProcessBuilder() {
        val pb = ProcessBuilder("/bin/bash", "-c", "echo hello")
        println(pb.environment())
        val env = mutableMapOf<String, String>()
        env["pb"] = "pb"
        pb.environment().putAll(env)
        println(pb.environment())
    }
}