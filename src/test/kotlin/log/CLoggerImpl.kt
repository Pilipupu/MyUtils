package org.zstack.zops.utils.log

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class CLoggerImpl : CLogger {
    private val logger: Logger

    private constructor(name: String) {
        logger = LogManager.getLogger(name)
    }

    private constructor(clazz: Class<*>) {
        logger = LogManager.getLogger(clazz)
    }

    override fun trace(msg: String?, e: Throwable?) {
        logger.trace(msg, e)
    }

    override fun trace(msg: String?) {
        logger.trace(msg)
    }

    override fun trace(msg: () -> Any?) {
        logger.trace(msg.toStringSafe())
    }

    override fun trace(t: Throwable?, msg: () -> Any?) {
        trace(msg.toStringSafe(), t)
    }

    override fun debug(msg: String?, e: Throwable?) {
        logger.debug(msg, e)
    }

    override fun debug(msg: String?) {
        logger.debug(msg)
    }

    override fun debug(msg: () -> Any?) {
        logger.debug(msg.toStringSafe())
    }

    override fun debug(t: Throwable?, msg: () -> Any?) {
        debug(msg.toStringSafe(), t)
    }

    override fun info(msg: String?, e: Throwable?) {
        logger.info(msg, e)
    }

    override fun info(msg: String?) {
        logger.info(msg)
    }

    override fun info(msg: () -> Any?) {
        logger.info(msg.toStringSafe())
    }

    override fun info(t: Throwable?, msg: () -> Any?) {
        info(msg.toStringSafe(), t)
    }

    override fun warn(msg: String?, e: Throwable?) {
        logger.warn(msg, e)
    }

    override fun warn(msg: String?) {
        logger.warn(msg)
    }

    override fun warn(msg: () -> Any?) {
        logger.warn(msg.toStringSafe())
    }

    override fun warn(t: Throwable?, msg: () -> Any?) {
        warn(msg.toStringSafe(), t)
    }

    override fun error(msg: String?, e: Throwable?) {
        logger.error(msg, e)
    }

    override fun error(msg: String?) {
        logger.error(msg)
    }

    override fun error(msg: () -> Any?) {
        logger.error(msg.toStringSafe())
    }

    override fun error(t: Throwable?, msg: () -> Any?) {
        error(msg.toStringSafe(), t)
    }

    override fun fatal(msg: String?, e: Throwable?) {
        logger.fatal(msg, e)
    }

    override fun fatal(msg: String?) {
        logger.fatal(msg)
    }

    override fun isTraceEnabled(): Boolean {
        return logger.isTraceEnabled
    }

    companion object {
        fun getLogger(name: String): CLogger {
            return CLoggerImpl(name)
        }

        fun getLogger(clazz: Class<*>): CLogger {
            return CLoggerImpl(clazz)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun (() -> Any?).toStringSafe(): String {
        return try {
            invoke().toString()
        } catch (e: Exception) {
            "Log message invocation failed: $e"
        }
    }
}