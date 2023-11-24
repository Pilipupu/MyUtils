import java.util.*

object SizeUtils {
    private const val T_SUFFIX = "T"
    private const val TB_SUFFIX = "TB"
    private const val t_SUFFIX = "t"
    private const val G_SUFFIX = "G"
    private const val GB_SUFFIX = "GB"
    private const val g_SUFFIX = "g"
    private const val M_SUFFIX = "M"
    private const val MB_SUFFIX = "MB"
    private const val m_SUFFIX = "m"
    private const val K_SUFFIX = "K"
    private const val KB_SUFFIX = "KB"
    private const val k_SUFFIX = "k"
    private const val B_SUFFIX = "B"
    private const val b_SUFFIX = "b"
    private val validSuffix = Arrays.asList(
        T_SUFFIX,
        TB_SUFFIX,
        t_SUFFIX,
        G_SUFFIX,
        GB_SUFFIX,
        g_SUFFIX,
        M_SUFFIX,
        MB_SUFFIX,
        m_SUFFIX,
        K_SUFFIX,
        KB_SUFFIX,
        k_SUFFIX,
        B_SUFFIX,
        b_SUFFIX
    )

    private fun isPositiveNumber(str: String): Boolean {
        return str.matches(Regex("\\d+"))
    }

    fun isSizeString2(str: String): Boolean {
        val suffix = str.substring(str.length - 2)
        if (!validSuffix.contains(suffix)) {
            return false
        }
        val num = str.substring(0, str.length - 2)
        return isPositiveNumber(num)
    }

    fun isSizeString(str: String): Boolean {
        val suffix = str.substring(str.length - 1)
        return if (!validSuffix.contains(suffix)) {
            isPositiveNumber(str)
        } else {
            val num = str.substring(0, str.length - 1)
            isPositiveNumber(num)
        }
    }

    fun sizeStringToBytes(str: String): Long {
        val numStr = Regex("[\\d|.]+").find(str)?.value
        println(numStr)
        val suffix = Regex("[a-zA-Z]+").find(str)?.value
        if (!validSuffix.contains(suffix)) {
            return str.toLong()
        }
        val size = numStr!!.toDouble()
        println(size)
        if (suffix == T_SUFFIX || suffix == t_SUFFIX || suffix == TB_SUFFIX) {
            return SizeUnit.TERABYTE.toByte(size.toDouble()).toLong()
        } else if (suffix == G_SUFFIX || suffix == g_SUFFIX || suffix == GB_SUFFIX) {
            return SizeUnit.GIGABYTE.toByte(size.toDouble()).toLong()
        } else if (suffix == M_SUFFIX || suffix == m_SUFFIX || suffix == MB_SUFFIX) {
            return SizeUnit.MEGABYTE.toByte(size.toDouble()).toLong()
        } else if (suffix == K_SUFFIX || suffix == k_SUFFIX || suffix == KB_SUFFIX) {
            return SizeUnit.KILOBYTE.toByte(size.toDouble()).toLong()
        } else if (suffix == B_SUFFIX || suffix == b_SUFFIX) {
            return SizeUnit.BYTE.toByte(size.toDouble()).toLong()
        }
        throw RuntimeException("should not be here,$str")
    }

    fun sizeStringToBytesNotThrowException(s: String) : Long {
        return try {
            sizeStringToBytes(s)
        } catch (t: Throwable) {
            -1
        }
    }
}