package events

abstract class EventBus {
    abstract fun <D> dispatch(event: Event<D>)
    abstract fun <D> subscribe(topic: String, callback: (D) -> Unit): Subscriber<D>
}