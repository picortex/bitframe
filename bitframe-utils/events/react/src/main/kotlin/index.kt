@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

import events.EventBus
import events.Subscriber
import react.useEffectOnce

fun <D> useEventHandler(
    bus: EventBus,
    topic: String,
    callback: (D) -> Unit
): Unit = useEffectOnce {
    val subscriber = bus.subscribe(topic, callback)
    useSubscriber(subscriber)
}

fun <D> useSubscriber(subscriber: Subscriber<D>) = useEffectOnce {
    cleanup { subscriber.unsubscribe() }
}