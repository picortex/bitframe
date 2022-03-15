package stream

import kotlin.js.JsExport

@JsExport
interface StreamCollector {
    fun stop(): Boolean
}