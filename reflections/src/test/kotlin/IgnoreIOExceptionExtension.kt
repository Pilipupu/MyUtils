import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler
import java.io.IOException


class IgnoreIOExceptionExtension : TestExecutionExceptionHandler {
    override fun handleTestExecutionException(context: ExtensionContext, throwable: Throwable) {
        if (throwable is IOException) {
            return
        }
        println(context.displayName)
    }
}