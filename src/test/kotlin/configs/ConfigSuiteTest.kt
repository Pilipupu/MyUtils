package configs

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag

@Tag("configs")
interface ConfigSuiteTest

open class InitDB {
    companion object {

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            val skip = getConfig(GlobalConfig.ZOPS_SKIP_MIGRATION, false).bool()
        }
    }
}