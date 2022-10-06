package events

import useEventHandler

fun <D> EventBus.useEventHandler(
    topic: String,
    callback: (D) -> Unit
): Unit = useEventHandler(bus = this, topic = topic, callback = callback)