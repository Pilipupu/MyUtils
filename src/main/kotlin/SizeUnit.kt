enum class SizeUnit {
    BYTE {
        val FULL_NAME = "Byte"
        val NAME = "B"

        override fun toByte(s: Double): Double {
            return s
        }

        override fun toKiloByte(s: Double): Double {
            return s / (k / b)
        }

        override fun toMegaByte(s: Double): Double {
            return s / (m / b)
        }

        override fun toGigaByte(s: Double): Double {
            return s / (g / b)
        }

        override fun toTeraByte(s: Double): Double {
            return s / (t / b)
        }

        override fun toPetaByte(s: Double): Double {
            return s / (p / b)
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toByte(s)
        }

        override fun getUnitValue(): Long {
            return b
        }
    },
    KILOBYTE {
        override fun toByte(s: Double): Double {
            return s * (k / b)
        }

        override fun toKiloByte(s: Double): Double {
            return s
        }

        override fun toMegaByte(s: Double): Double {
            return s / (m / k)
        }

        override fun toGigaByte(s: Double): Double {
            return s / (g / k)
        }

        override fun toTeraByte(s: Double): Double {
            return s / (t / k)
        }

        override fun toPetaByte(s: Double): Double {
            return s / (p / k)
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toKiloByte(s)
        }

        override fun getUnitValue(): Long {
            return k
        }
    },
    MEGABYTE {
        override fun toByte(s: Double): Double {
            return s * (m / b)
        }

        override fun toKiloByte(s: Double): Double {
            return s * (m / k)
        }

        override fun toMegaByte(s: Double): Double {
            return s
        }

        override fun toGigaByte(s: Double): Double {
            return s / (g / m)
        }

        override fun toTeraByte(s: Double): Double {
            return s / (t / m)
        }

        override fun toPetaByte(s: Double): Double {
            return s / (p / m)
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toMegaByte(s)
        }

        override fun getUnitValue(): Long {
            return m
        }
    },
    GIGABYTE {
        override fun toByte(s: Double): Double {
            return s * (g / b)
        }

        override fun toKiloByte(s: Double): Double {
            return s * (g / k)
        }

        override fun toMegaByte(s: Double): Double {
            return s * (g / m)
        }

        override fun toGigaByte(s: Double): Double {
            return s
        }

        override fun toTeraByte(s: Double): Double {
            return s / (t / g)
        }

        override fun toPetaByte(s: Double): Double {
            return s / (p / g)
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toGigaByte(s)
        }

        override fun getUnitValue(): Long {
            return g
        }
    },
    TERABYTE {
        override fun toByte(s: Double): Double {
            return s * (t / b)
        }

        override fun toKiloByte(s: Double): Double {
            return s * (t / k)
        }

        override fun toMegaByte(s: Double): Double {
            return s * (t / m)
        }

        override fun toGigaByte(s: Double): Double {
            return s * (t / g)
        }

        override fun toTeraByte(s: Double): Double {
            return s
        }

        override fun toPetaByte(s: Double): Double {
            return s / (p / g)
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toGigaByte(s)
        }

        override fun getUnitValue(): Long {
            return g
        }

    },
    PETABYTE{
        override fun toByte(s: Double): Double {
            return s * (p / b)
        }

        override fun toKiloByte(s: Double): Double {
            return s * (p / k)
        }

        override fun toMegaByte(s: Double): Double {
            return s * (p / m)
        }

        override fun toGigaByte(s: Double): Double {
            return s * (p / g)
        }

        override fun toTeraByte(s: Double): Double {
            return s * (p / t)
        }

        override fun toPetaByte(s: Double): Double {
            return s
        }

        override fun convert(s: Double, src: SizeUnit): Double {
            return src.toPetaByte(s)
        }

        override fun getUnitValue(): Long {
            return p
        }
    };

    companion object {
        fun fromString(s: String?): SizeUnit {
            return if ("b".equals(s, ignoreCase = true)) {
                BYTE
            } else if ("k".equals(s, ignoreCase = true)) {
                KILOBYTE
            } else if ("m".equals(s, ignoreCase = true)) {
                MEGABYTE
            } else if ("g".equals(s, ignoreCase = true)) {
                GIGABYTE
            } else if ("t".equals(s, ignoreCase = true)) {
                TERABYTE
            } else if ("p".equals(s, ignoreCase = true)) {
                PETABYTE
            } else {
                throw IllegalArgumentException(String.format("unknown size unit[%s]", s))
            }
        }

    }


    val b: Long = 1
    val k = b * 1024
    val m = k * 1024
    val g = m * 1024
    val t = g * 1024
    val p = t * 1024

    abstract fun toByte(s: Double): Double

    abstract fun toKiloByte(s: Double): Double

    abstract fun toMegaByte(s: Double): Double

    abstract fun toGigaByte(s: Double): Double

    abstract fun toTeraByte(s: Double): Double

    abstract fun toPetaByte(s: Double): Double

    abstract fun convert(s: Double, src: SizeUnit): Double

    abstract fun getUnitValue(): Long
}
