package demo
import javassist.ClassPool
import javassist.bytecode.AnnotationsAttribute
import javassist.bytecode.ConstPool
import javassist.bytecode.MethodInfo
import javassist.bytecode.annotation.Annotation
import javassist.bytecode.annotation.StringMemberValue
import org.junit.jupiter.api.Test
import reflection.Reflection


class GetAllClassesTest {
    @Test
    fun getAllClasses() {
        Reflection.reflect.getSubTypesOf(demo.Test::class.java).forEach {
            val ctClass = ClassPool.getDefault().get(it.name)
            val ctMethod = ctClass.getDeclaredMethod("test")
            val methodInfo: MethodInfo = ctMethod.methodInfo
            val constPool = methodInfo.constPool

            saveAnnotation(constPool, methodInfo, "Tag", "value", "demo")
        }
    }
}


//设置注解
fun saveAnnotation(constPool: ConstPool?, methodInfo: MethodInfo, typeName: String?, key: String?, value: String?) {
    val annotationsAttribute = AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag)
    //这个必须还是输入全类名
    val annotation = Annotation(typeName, constPool)
    annotation.addMemberValue(key, StringMemberValue(value, constPool))
    annotationsAttribute.addAnnotation(annotation)
    methodInfo.addAttribute(annotationsAttribute)
}