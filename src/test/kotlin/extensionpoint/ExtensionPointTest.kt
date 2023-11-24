package extensionpoint

import io.mockk.every
import io.mockk.mockkConstructor
import json.jsonEncode
import org.apache.commons.lang3.ClassUtils
import org.junit.jupiter.api.Test
import org.zstack.zops.core.extensionponints.ExtensionPoint
import reflection.Reflection
import java.lang.annotation.Inherited
import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.allSupertypes
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Created by IntelliJ IDEA.
 * @Author : jingwang
 * @create 2022/8/10 10:17 AM
 *
 */
class ExtensionPointTest {
    @OptIn(ExperimentalTime::class)
    @Test
    fun testExtensionPoint() {
        lateinit var extensions: List<Class<out ExtensionPoint>>
        lateinit var extensionInterfaces: List<Class<out ExtensionPoint>>
        println(measureTime {
            val classSubOfExtensionPoint = Reflection.reflect.getSubTypesOf(ExtensionPoint::class.java)
            extensions = classSubOfExtensionPoint.filter {
                !Modifier.isAbstract(it.modifiers)
            }


            extensionInterfaces = classSubOfExtensionPoint.filter {
                Modifier.isAbstract(it.modifiers) && it.name != ExtensionPoint::class.java.name
            }
            extensionInterfaces.forEach {
                println(it.simpleName)
            }
        })
        println(measureTime {
            extensions.forEach {
                it.getDeclaredConstructor().newInstance()
            }
        })

        println(measureTime {
            extensions.forEach {
                val cs = ClassUtils.getAllInterfaces(it) intersect extensionInterfaces
                cs.forEach {
                    println(it.simpleName)
                }
            }
        })
        println(measureTime {
            extensions.forEach {
                val cs  = it.kotlin.allSuperclasses.map { it.java } intersect  extensionInterfaces
                cs.forEach {
                    println(it.simpleName)
                }
            }
        })
    }

    @Test
    fun test2() {
        ClassUtils.getAllInterfaces(ComponentObj::class.java).filter {
            Modifier.isAbstract(it.modifiers)
        }.forEach {
            println(it.simpleName)
        }
//        ClassUtils.getAllSuperclasses(ComponentObj.javaClass).filter {
//            Modifier.isAbstract(it.modifiers)
//        }.forEach {
//            println(it.simpleName)
//        }
    }

    @Test
    fun test3() {
        mockkConstructor(A::class)
    }

    @Test
    fun test4() {
        println(TestClass::class.annotations.size)
        println(TestClass::class.findAnnotation<ExtensionPointOrders>()?.extensionPoint?.jsonEncode())
    }

    class A(
        val s1: String,
        val s2: String
    ) {
        fun f1() {

        }

        fun f2() {

        }
    }
}

interface Component : ExtensionPoint {

}

interface Component2 : ExtensionPoint {

}

interface Component3 : ExtensionPoint {

}
interface TT {

}

@ExtensionPointOrders([1,2])
abstract class Ex1 : Component {

}

@ExtensionPointOrders([3,4])
class TestClass : Ex1(), Component2, TT {

}

object ComponentObj : Ex1(){

}

@Repeatable
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class ExtensionPointOrders(
    val extensionPoint: IntArray
)