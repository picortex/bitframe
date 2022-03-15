@file:Suppress("NON_EXPORTABLE_TYPE")

package stream

import kotlin.js.JsExport

@JsExport
interface Stream<D> {
    fun collect(callback: (D) -> Unit): StreamCollector
}