package configs

import java.lang.RuntimeException

object Configs {
    internal val configs = mutableMapOf<String, Config>()

    internal fun define(name: String, config: Config) {
        val old = configs[name]
        if (old != null) {
            throw InternalError("duplicate config[$name], find $old and $config")
        }

        configs[name] = config
    }
}

class EnvConfig(val name: String, private val default: Any) : Config {
    private var value: Any = Unit

    init {
        Configs.define(name, this)
    }

    override fun name(): String {
        return name
    }

    override fun string(): String {
        return value()
    }

    private fun value(): String {
        if (value != Unit) {
            return value.toString()
        }

        val v = System.getProperty(name) ?: System.getenv(name)
        value = if (v == null) {
            if (default == Unit) {
                throw RuntimeException("cannot find config[${name}] in environment variables, and it's default value not set")
            }
            default
        } else {
            v
        }

        return value.toString()
    }

    override fun bool(): Boolean {
        return value().toBoolean()
    }
}

interface Config {
    fun name(): String
    fun string(): String
    fun bool(): Boolean
}

fun getConfig(config: GlobalConfig, default: Any) : EnvConfig {
    return EnvConfig(config.name, default)
}