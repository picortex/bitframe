package events

import useEventHandler

fun <D> EventBus.useEventHandler(
    eventId: String,
    callback: (D) -> Unit
): Unit = useEventHandler(bus = this, topic = eventId, callback = callback)