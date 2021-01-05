package org.error

import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

class OperationFailureError : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}

class WrongArgError : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}

class InternalError : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}

class IfErrorThen(private val fn: ()->Unit) {
    infix fun then(tfn: (Exception) -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            tfn(e)
        }
    }
}

fun ifError(fn: () -> Unit): IfErrorThen {
    return IfErrorThen(fn)
}

fun logException(fn: () -> Any?): Any? {
    return try {
        fn()
    } catch (e: Exception) {
        logger.warn(e) { "unhandled exception happened" }
    }
}