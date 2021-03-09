package learning.kotlin

import org.junit.jupiter.api.Test
import java.io.BufferedInputStream
import java.io.InputStream


interface Base {
    fun base()
    fun play()
}

interface Base2 {
    fun base2()
    fun play2()
}

class BaseImpl : Base {
    override fun base() {
        println("impl")
    }

    override fun play() {
        println("base play")
    }
}
class BaseImpl2 : Base2 {
    override fun base2() {
        println("impl")
    }

    override fun play2() {
        println("base play")
    }
}
//用来代替继承
class BaseProxy(private val b : Base, private val c : Base2) : Base by b, Base2 by c {
    override fun base() {
        b.base()
    }

    fun get() {
        play()
    }


}

class ByTest {
    @Test
    fun demo() {
        val b = BaseProxy(BaseImpl(),BaseImpl2())
        b.get()
        b.play()

        val c = BufferedInputStream(InputStream.nullInputStream())
    }
}