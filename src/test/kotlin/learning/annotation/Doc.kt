package learning.annotation

import action.JobActionInput
import action.actionSchemas
import json.Coder
import org.junit.jupiter.api.Test
import reflection.Reflection

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Doc(
    val desc: String = ""
)

class Demo {
    @Test
    fun a() {
        val apis = actionSchemas
        apis.map { (name, s) -> name to Coder.jsonPrettyEncode(s.input.schema()) }.toMap()
    }
}