package org.zstack.zops.utils.log

val logger = Log.getLogger(Log::class.java)

class Log {
    companion object {
        fun getLogger(clazz: Class<*>): CLogger {
            return CLoggerImpl.getLogger(clazz)
        }

        fun getLogger(className: String): CLogger {
            return CLoggerImpl.getLogger(className)
        }
    }
}