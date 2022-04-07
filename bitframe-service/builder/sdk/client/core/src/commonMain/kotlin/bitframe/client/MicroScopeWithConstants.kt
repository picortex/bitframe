@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import kotlin.js.JsExport

@JsExport
interface MicroScopeWithConstants<out I, out S, out C> : MicroScope<I, S> {
    val constants: C
}