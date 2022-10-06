@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package events

import kotlin.js.JsExport

abstract class Subscriber<D>(val topic: String) {
    abstract operator fun invoke(data: D)
    abstract fun unsubscribe()
}