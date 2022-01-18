@file:JsExport

package events

import kotlin.js.JsExport

abstract class Subscriber<D>(val topic: String) {
    abstract operator fun invoke(data: D)
    abstract fun unsubscribe()
}