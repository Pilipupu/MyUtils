package control.retry

class Continue
class Timeout

fun retryUntilTimeout(timeoutInMills: Long, intervalInMills: Long, fn: () -> Any): Any {
    val expired = System.currentTimeMillis() + timeoutInMills
    while (expired > System.currentTimeMillis()) {
        val ret = fn()
        if (ret !is Continue) {
            return ret
        }

        Thread.sleep(intervalInMills)
    }

    return Timeout()
}