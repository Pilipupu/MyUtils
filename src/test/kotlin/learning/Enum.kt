package learning

enum class CobblerCommand {
    COBBLER_IMPORT {
        override fun value(): String {
            return "cobbler import *"
        }
    },
    COBBLER_SYSTEM_ADD {
        override fun value(): String {
            return "cobbler system add *"
        }
    },
    COBBLER_SYSTEM_REMOVE {
        override fun value(): String {
            return "cobbler system remove *"
        }
    },
    COBBLER_SYSTEM_EDIT {
        override fun value(): String {
            return "cobbler system edit *"
        }
    },
    COBBLER_SYSTEM_LIST {
        override fun value(): String {
            return "cobbler system list"
        }
    },
    COBBLER_PROFILE_ADD {
        override fun value(): String {
            return "cobbler system add *"
        }
    },
    COBBLER_PROFILE_RENAME {
        override fun value(): String {
            return "cobbler system rename *"
        }
    },
    COBBLER_PROFILE_REMOVE {
        override fun value(): String {
            return "cobbler system remove *"
        }
    },
    COBBLER_PROFILE_LIST {
        override fun value(): String {
            return "cobbler system list"
        }
    };

    abstract fun value() : String
}