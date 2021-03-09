package os.bash

import control.retry.Continue
import control.retry.Timeout
import control.retry.retryUntilTimeout
import error.OperationFailureError
import error.logException
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lang.ifFalse
import mu.KotlinLogging
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

internal val logger = KotlinLogging.logger {  }

object BashMock {
    var runShell: ((cmd: String) -> BashReturn)? = null
}

class CheckingCommandReturn(retCode: Int, stdout: String, stderr: String, command: String, val triedTimes: Int) : BashReturn(retCode, stdout, stderr, command)

open class BashReturn(val retCode: Int, val stdout: String, val stderr: String, val command: String) {
    var checkCommandReturn: CheckingCommandReturn? = null
    var expectRetCode: Int = 0

    fun screenOut(): String {
        return if (stdout.isBlank()) {
            stderr
        } else {
            stdout
        }
    }

    private fun isCheckCommandSuccess(): Boolean {
        if (checkCommandReturn != null) {
            return checkCommandReturn!!.success(checkCommandReturn!!.expectRetCode)
        }

        return true
    }

    fun success(expectRetCode: Int = 0): Boolean {
        if (!isCheckCommandSuccess()) {
            return false
        }

        return expectRetCode == retCode
    }

    fun ifNotSuccess(expectRetCode: Int = 0, msg: BashReturn.() -> String): BashReturn {
        if (!success(expectRetCode)) {
            var err = if (isCheckCommandSuccess()) {
                """Shell Command Failure:

$command

ret code: $retCode
stdout: $stdout
stderr: $stderr
"""
            } else {
                val cbr = checkCommandReturn!!
                """Shell Command Failure[Checking Command Failure Happens]:

The original command is success, but the checking command failed to check status oriented by the original command

Original Command Details:

$command

ret code: $retCode
stdout: $stdout
stderr: $stderr

Checking Command Details:

${cbr.command}

tired times: ${cbr.triedTimes}
ret code: ${cbr.retCode}
stdout: ${cbr.stdout}
stderr: ${cbr.stderr}
"""
            }

            val m = msg(this)
            if (!m.isBlank()) {
                err = "$m\n$err"
            }

            throw OperationFailureError(err)
        }

        return this
    }
}

class Bash(
    var command: String,
    var pipeFail: Boolean = false,
    var arguments: Map<String, Any>? = null,
    var dir: String? = null,
    var nolog: Boolean = false,
    var expectRetCode: Int = 0,
    var checkCommand: String? = null,
    var checkCommandTimeoutInMillSecond: Long = 5000,
    var checkCommandIntervalInMillSecond: Long = 500,
    var checkCommandFailureMessage: String = "",
    var checkFunction: (() -> Boolean)? = null,
    var checkCommandSuccessReturnCode: Int = 0,
    var env: Map<String, String>? = null,
    var outputToScreen: Boolean = false,
    var timeoutInMillSeconds: Long? = null
) {
    init {
        if (arguments != null) {
            command = command.format(arguments)

            if (checkCommand != null) {
                checkCommand = checkCommand!!.format(arguments)
            }
        }

        if (pipeFail) {
            command = "set -o pipefail; $command"
        }
    }

    fun execute(stdinWriter: ((OutputStream) -> Unit)? = null): BashReturn {
        val r = run(command, stdinWriter)

        if (r.success(expectRetCode) && checkCommand != null) {
            var cbr: BashReturn? = null

            val t = retryUntilTimeout(timeoutInMills = checkCommandTimeoutInMillSecond, intervalInMills = checkCommandIntervalInMillSecond) {
                val cr = run(checkCommand!!)
                cbr = cr

                return@retryUntilTimeout if (cr.retCode == checkCommandSuccessReturnCode) {
                    cr
                } else {
                    Continue()
                }
            }

            if (t is Timeout) {
                if (checkCommandFailureMessage.isNotBlank() && !nolog) {
                    logger.trace { "[Bash ($command)] $checkCommandFailureMessage" }
                }

                if (!nolog) {
                    logger.trace { "[Bash ($command)] check command[$checkCommand] keep failing in period of $checkCommandTimeoutInMillSecond ms" }
                }

                val cr = cbr!!
                r.apply {
                    checkCommandReturn = CheckingCommandReturn(
                        retCode = cr.retCode,
                        stdout = cr.stdout,
                        stderr = cr.stderr,
                        triedTimes = (checkCommandTimeoutInMillSecond / checkCommandIntervalInMillSecond).toInt(),
                        command = checkCommand!!
                    ).apply {
                        expectRetCode = checkCommandSuccessReturnCode
                    }
                }
            }
        }

        return r.apply {
            expectRetCode = this@Bash.expectRetCode
        }
    }

    private fun run(cmd: String, stdinWriter: ((OutputStream) -> Unit)? = null): BashReturn {
        BashMock.runShell?.let {
            return it(cmd)
        }

        val pb = ProcessBuilder("/bin/bash", "-c", cmd)
        dir?.let {
            pb.directory(File(it))
        }


        if (!outputToScreen) {
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE)
            pb.redirectError(ProcessBuilder.Redirect.PIPE)
        } else {
            pb.inheritIO()
        }

        env?.let {
            pb.environment().putAll(it)
        }

        if (!nolog) {
            logger.trace { "[Bash] $cmd" }
        }

        val p = pb.start()
        var stdoutStream: ByteArrayOutputStream? = null
        var stderrStream: ByteArrayOutputStream? = null
        var consumerThread: Thread? = null
        if (!outputToScreen) {
            stdoutStream = ByteArrayOutputStream()
            stderrStream = ByteArrayOutputStream()

            consumerThread = thread {
                runBlocking {
                    launch {
                        val sc = Scanner(p.inputStream)
                        val ps = PrintStream(stdoutStream)
                        while (sc.hasNextLine()) {
                            ps.println(sc.nextLine())
                        }
                    }

                    launch {
                        val sc = Scanner(p.errorStream)
                        val ps = PrintStream(stderrStream)
                        while (sc.hasNextLine()) {
                            ps.println(sc.nextLine())
                        }
                    }
                }
            }
        }

        if (stdinWriter != null) {
            thread {
                logException {
                    stdinWriter(p.outputStream)
                }
            }
        }

        if (timeoutInMillSeconds != null) {
            p.waitFor(timeoutInMillSeconds!!, TimeUnit.MILLISECONDS).ifFalse {
                p.destroy()
            }
        } else {
            p.waitFor()
        }

        var stdout = ""
        var stderr = ""

        if (!outputToScreen) {
            consumerThread!!.join()
            stdout = String(stdoutStream!!.toByteArray())
            stderr = String(stderrStream!!.toByteArray())
        }

        if (!nolog) {
            logger.trace { "[Bash ($cmd)] retcode=${p.exitValue()}, stdout=$stdout, stderr=$stderr" }
        }

        return BashReturn(p.exitValue(), stdout, stderr, cmd)
    }
}

fun String.asBash(path: String = ""): Bash {
    return if (path.isEmpty()) Bash(command = this) else Bash(command = this, dir=path)
}

