package org.reflection

import org.reflections.Reflections
import org.reflections.scanners.*
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder

object Reflection {
    val reflect = Reflections(
        ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("org.demo"))
            .setScanners(
                SubTypesScanner(), MethodAnnotationsScanner(), FieldAnnotationsScanner(),
                TypeAnnotationsScanner(), MethodParameterScanner()
            ).filterInputsBy(
                FilterBuilder().includePackage("org.demo")
            )
    )
}