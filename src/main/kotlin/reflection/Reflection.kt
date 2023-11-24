package reflection
import org.reflections.Reflections
import org.reflections.scanners.*
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder

object Reflection {
    val reflect = Reflections(
        ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("extensionpoint"))
            .setScanners(
                SubTypesScanner(), MethodAnnotationsScanner(), FieldAnnotationsScanner(),
                TypeAnnotationsScanner(), MethodParameterScanner()
            ).filterInputsBy(
                FilterBuilder().includePackage("extensionpoint")
            )
    )

    val reflectForInput = Reflections(
        ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("action"))
            .setScanners(
                SubTypesScanner(), MethodAnnotationsScanner(), FieldAnnotationsScanner(),
                TypeAnnotationsScanner(), MethodParameterScanner()
            ).filterInputsBy(
                FilterBuilder().includePackage("action")
            )
    )
}