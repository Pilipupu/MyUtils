package json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr353.JSR353Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import java.util.*
import kotlin.reflect.KClass

object Coder {
    val json = ObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        registerModules(KotlinModule(), JSR353Module(), ParameterNamesModule(), Jdk8Module(), JavaTimeModule())
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE)
        enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
        configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
        configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true)
        configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
        configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
    }

    fun jsonEncode(obj: Any): String {
        return json.writeValueAsString(obj)
    }

    fun <T : Any> jsonDecode(str: String, clz: KClass<T>): T {
        println(str)
        return json.readerFor(clz.java).readValue(str)
    }

    fun <T : Any> jsonDecodeAsList(str: String, clz: KClass<T>): List<T> {
        val t = json.typeFactory.constructCollectionType(List::class.java, clz.java)
        return json.readValue(str, t)
    }

    fun jsonPrettyEncode(obj: Any): String {
        return json.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
    }

    fun jsonReadTree(str: String): JsonNode {
        return json.readTree(str)
    }

    fun jsonFind(root: JsonNode, fn: JsonNode.() -> Boolean): JsonNode? {
        val ret = jsonSearch(root, fn)
        if (ret.isEmpty()) {
            return null
        }

        return ret.values.iterator().next()
    }

    fun jsonSearch(root: JsonNode, fn: JsonNode.() -> Boolean): Map<String, JsonNode> {
        val path = Stack<String>()
        val ret = mutableMapOf<String, JsonNode>()
        jsonSearch(root, path, ret, fn)
        return ret
    }

    private fun jsonSearch(n: JsonNode, path: Stack<String>, ret: MutableMap<String, JsonNode>, fn: JsonNode.() -> Boolean) {
        if (fn(n)) {
            ret[path.joinToString("/")] = n
        }

        if (n.isArray) {
            n.forEachIndexed { index, jn ->
                path.push(index.toString())
                jsonSearch(jn, path, ret, fn)
                path.pop()
            }
        }

        if (n.isObject) {
            n.fields().forEach { (name, jn) ->
                path.push(name)
                jsonSearch(jn, path, ret, fn)
                path.pop()
            }
        }
    }

    fun jsonNodeToAny(node: JsonNode): Any? {
        return when {
            node.isTextual -> node.asText()
            node.isShort -> node.asInt()
            node.isInt -> node.asInt()
            node.isDouble -> node.asDouble()
            node.isFloat -> node.asDouble()
            node.isLong -> node.asLong()
            node.isBigInteger -> node.bigIntegerValue()
            node.isNull -> null
            node.isBinary -> node.binaryValue()
            node.isBigDecimal -> node.decimalValue()
            node.isBoolean -> node.asBoolean()
            node.isFloatingPointNumber -> node.asDouble()
            node.isPojo || node.isObject -> node.fields().iterator().asSequence().toList().map { (k, v) -> k to jsonNodeToAny(v) }.toMap()
            node.isArray -> node.iterator().asSequence().toList().map { jsonNodeToAny(it) }
            else -> node.asText()
        }
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

