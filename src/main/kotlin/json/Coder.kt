package json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr353.JSR353Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import kotlin.reflect.KClass

object Coder {
    val json = ObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        registerModules(KotlinModule(), JSR353Module(), ParameterNamesModule(), Jdk8Module(), JavaTimeModule())
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE)
        enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
    }

    fun jsonEncode(obj: Any): String {
        return json.writeValueAsString(obj)
    }

    fun <T : Any> jsonDecode(str: String, clz: KClass<T>): T {
        return json.readerFor(clz.java).readValue(str)
    }

    fun <T : Any> jsonDecodeAsList(str: String, clz: KClass<T>): List<T> {
        val t = json.typeFactory.constructCollectionType(List::class.java, clz.java)
        return json.readValue(str, t)
    }

    fun jsonPrettyEncode(obj: Any): String {
        return json.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
    }
}


fun Any.jsonEncode(): String {
    return Coder.jsonEncode(this)
}

fun <T : Any> String.jsonDecode(kclz: KClass<T>): T {
    return Coder.jsonDecode(this, kclz)
}

fun <T : Any> String.jsonDecodeAsList(kclaz: KClass<T>): List<T> {
    return Coder.jsonDecodeAsList(this, kclaz)
}

