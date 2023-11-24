import org.junit.jupiter.api.Test

abstract class Animal(open var test: String) {
    var bark: String
    var drink: String

    init {
        bark = bark()
        drink = drink()
        test = test()
    }

    abstract fun bark() : String
    open fun drink() : String {
        return "animal drink"
    }

    abstract fun test() : String
}

class Dog(test: String) : Animal(test) {
    override fun bark(): String {
        return "wang wang"
    }

    override fun drink(): String {
        return "dog drink"
    }

    override fun test(): String {
        println(test)
        return test
    }
}

class AbstractTest {
    @Test
    fun testAbstract() {
        println(Dog("test").drink)
    }

    @Test
    fun testFlatMap() {
        val pureLine = "pure line"
        val regex = Regex("([a-z]+)")

        val (name, _) = regex.find(pureLine)!!.destructured

        println(name)
    }
}

class A {
    var a: String? = b()

    fun b() : String {
        if (a == null) {
            println("bbbbbb")
            return "b"
        } else {
            println("aaaaaa")
            return a as String
        }
    }
}

class TestA {
    @Test
    fun testA() {
        val a = A().apply {
            a = "hello"
        }
        a.a
        a.a
    }
}