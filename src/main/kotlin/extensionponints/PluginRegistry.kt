package org.zstack.zops.core.extensionponints

import kotlin.reflect.KClass

/**
 * Created by IntelliJ IDEA.
 * @Author : jingwang
 * @create 2022/8/10 10:04 AM
 *
 */
interface PluginRegistry {
    fun <T : ExtensionPoint> getExtensionList(clazz: KClass<T>): Set<T>

    fun <T : ExtensionPoint> getExtension(clazz: KClass<T>): T
}