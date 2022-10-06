@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package events

import kotlin.js.JsExport

abstract class EventBus {
    abstract fun <D> dispatch(event: Event<D>)
    abstract fun <D> subscribe(topic: String, callback: EventCallback<D>): Subscriber<D>
}