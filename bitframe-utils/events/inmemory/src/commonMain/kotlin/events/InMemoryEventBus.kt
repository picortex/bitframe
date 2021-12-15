package events

import live.Live

class InMemoryEventBus : EventBus() {
    private val dispatchers = mutableMapOf<String, Live<Any>>()
    private val subscribers = mutableMapOf<String, MutableList<Subscriber<Any>>>()

    override fun <D> dispatch(event: Event<D>) {
        val live: Live<out Any>? = dispatchers[event.id]
        if (live == null) {
            val l = Live(event.data)
            dispatchers[event.id] = l as Live<Any>
            l.watch { value ->
                subscribers[event.id]?.forEach { it.invoke(value) }
            }
        } else {
            (live as Live<Any>).value = event.data as Any
        }
    }

    override fun <D> subscribe(eventId: String, callback: (D) -> Unit): Subscriber<D> {
        val list = subscribers.getOrPut(eventId) { mutableListOf() }
        val sub = SubscriberImpl(eventId, callback, list)
        list.add(sub as Subscriber<Any>)
        return sub
    }
}