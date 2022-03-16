@file:Suppress("NON_EXPORTABLE_TYPE")

package stream

import kotlin.js.JsExport

@JsExport
interface LiveStream<D> : Stream<D> {
    val value: D
}