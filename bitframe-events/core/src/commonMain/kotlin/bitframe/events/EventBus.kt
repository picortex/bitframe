@file:JsExport

package bitframe.events

import kotlin.js.JsExport

abstract class EventBus {
    abstract fun <D> dispatch(event: Event<D>)
    abstract fun <D> subscribe(eventId: String, callback: (D) -> Unit): Subscriber<D>
}