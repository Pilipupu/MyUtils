package action

import kotlin.reflect.KClass


class AddISOInput : JobActionInput() {
    @Doc
    lateinit var name: String

    lateinit var path: String

    var dem: Boolean = true
}

open class JobActionInput {
    var jobUuid: String? = null
}

class ActionSchema(val input: Schema, val output: Schema)
val actionSchemas = mutableMapOf<String, ActionSchema>()

interface Schema {
    fun schema(): Map<String, Any>
    fun <T: Any> validate(data: Any, type: KClass<T>, collectionItemClass: KClass<*>? = null): T
}