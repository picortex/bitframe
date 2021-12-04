@file:JsExport

package events

import kotlin.js.JsExport

abstract class Subscriber<D>(val eventId: String) {
    abstract operator fun invoke(data: D)
    abstract fun unsubscribe()
}