package org.zstack.zops.utils.log

interface CLogger {
    fun trace(msg: String?, e: Throwable?)

    fun trace(msg: String?)

    fun debug(msg: String?, e: Throwable?)

    fun debug(msg: String?)

    fun info(msg: String?, e: Throwable?)

    fun info(msg: String?)

    fun warn(msg: String?, e: Throwable?)

    fun warn(msg: String?)

    fun error(msg: String?, e: Throwable?)

    fun error(msg: String?)

    fun fatal(msg: String?, e: Throwable?)

    fun fatal(msg: String?)

    fun isTraceEnabled(): Boolean

    fun trace(msg: () -> Any?)

    fun debug(msg: () -> Any?)

    fun info(msg: () -> Any?)

    fun warn(msg: () -> Any?)

    fun error(msg: () -> Any?)

    fun trace(t: Throwable?, msg: () -> Any?)

    fun debug(t: Throwable?, msg: () -> Any?)

    fun info(t: Throwable?, msg: () -> Any?)

    fun warn(t: Throwable?, msg: () -> Any?)

    fun error(t: Throwable?, msg: () -> Any?)
}