import events.EventBus
import react.useEffectOnce

fun <D> useEventHandler(
    bus: EventBus,
    topic: String,
    callback: (D) -> Unit
): Unit = useEffectOnce {
    val subscriber = bus.subscribe(topic, callback)
    cleanup { subscriber.unsubscribe() }
}