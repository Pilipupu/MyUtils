import org.junit.jupiter.api.Test

class resource {
    @Test
    fun test() {
        println(getResource("env/demo").readText())
        println(hasResource("demo"))
    }
}