@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import later.Later
import kotlin.js.JsExport

@JsExport
interface Reader<P, R> {
    fun load(params: P): Later<R>
}