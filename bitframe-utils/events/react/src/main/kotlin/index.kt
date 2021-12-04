import events.EventBus
import react.useEffectOnce

fun <D> useEventHandler(
    bus: EventBus,
    eventId: String,
    callback: (D) -> Unit
): Unit = useEffectOnce {
    val subscriber = bus.subscribe(eventId, callback)
    cleanup { subscriber.unsubscribe() }
}