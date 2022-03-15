@file:Suppress("NON_EXPORTABLE_TYPE")

package stream

import kotlin.js.JsExport

@JsExport
interface StreamSender<D> {
    fun launch(block: () -> Unit)
    fun send(value: D)
}