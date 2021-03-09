package demo.funcPlus
import org.junit.jupiter.api.Test

class FuncPlusTest {
    val a = {
        println("demo.funcPlus.a")
    }

    @Test
    fun plus() {
        var b = {
            a
            println("b")
        }()
    }
}


fun a() {
    println("demo.funcPlus.a")
}