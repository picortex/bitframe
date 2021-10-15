import bitframe.events.EventBus
import react.useEffectOnce

fun <D> useEventHandler(
    bus: EventBus,
    eventId: String,
    callback: (D) -> Unit
) = useEffectOnce {
    val subscriber = bus.subscribe(eventId, callback)
    cleanup { subscriber.unsubscribe() }
}