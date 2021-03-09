package lang

fun Boolean.ifFalse(fn: ()->Unit) {
    if (!this) {
        fn()
    }
}