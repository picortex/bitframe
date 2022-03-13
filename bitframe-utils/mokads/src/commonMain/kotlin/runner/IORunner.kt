@file:Suppress("NON_EXPORTABLE_TYPE")

package runner

import kotlin.js.JsExport

@JsExport
interface IORunner<in I, out O> {
    fun start(input: I)
    fun run(input: I): O
}