package eventbus

import kotlin.concurrent.thread

class Event(
    val path: String,
    val data: Any?
)

typealias EventListenerFunc = (Event) -> Unit
//用于trigger的
typealias RemovableEventListenerFunc = (Event) -> Boolean

interface EventBus {
    fun listen(path: String, signalMode: Boolean = false, fn: EventListenerFunc): () -> Unit
    fun trigger(path: String, signalMode: Boolean = false, fn: RemovableEventListenerFunc): () -> Unit
    fun publish(path: String, data: Any?)

    fun startMainThread()
}

internal class EventBusImpl : EventBus {
    init {
        startMainThread()
    }

    override fun listen(path: String, signalMode: Boolean, fn: EventListenerFunc): () -> Unit {
        TODO("Not yet implemented")
    }

    override fun trigger(path: String, signalMode: Boolean, fn: RemovableEventListenerFunc): () -> Unit {
        TODO("Not yet implemented")
    }

    override fun publish(path: String, data: Any?) {
        TODO("Not yet implemented")
    }

    override fun startMainThread() {
        thread {

        }
    }

}