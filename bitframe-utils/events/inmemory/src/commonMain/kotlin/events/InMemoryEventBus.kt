package events

import live.value
import live.MutableLive

class InMemoryEventBus : EventBus() {
    private val dispatchers = mutableMapOf<String, MutableLive<Any>>()
    private val subscribers = mutableMapOf<String, MutableList<Subscriber<Any>>>()

    override fun <D> dispatch(event: Event<D>) {
        val live: MutableLive<out Any>? = dispatchers[event.topic]
        if (live == null) {
            val l = MutableLive(event.data)
            dispatchers[event.topic] = l as MutableLive<Any>
            l.watch { value ->
                subscribers[event.topic]?.forEach { it.invoke(value) }
            }
        } else {
            (live as MutableLive<Any>).value = event.data as Any
        }
    }

    override fun <D> subscribe(topic: String, callback: (D) -> Unit): Subscriber<D> {
        val list = subscribers.getOrPut(topic) { mutableListOf() }
        val sub = SubscriberImpl(topic, callback, list)
        list.add(sub as Subscriber<Any>)
        return sub
    }
}