package raid

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.concurrent.thread

abstract class Animal {
    var uuid = ""
    var name = ""
    init {
        println("animal")
        bark()
        if (name.isNotBlank()) {
            uuid = UUID.randomUUID().toString()
        } else {
            println()
        }
    }

    open fun bark() {
        println("animal bark")
    }
}

class Dog : Animal() {
    init {
        bark()
        thread {
            bark()
            Thread.sleep(3000)
        }.start()
    }

    override fun bark() {
        super.bark()
        println("dog bark")
    }
}

class testAnimal {
    @Test
    fun testAnimal() {
        Dog()
    }
}