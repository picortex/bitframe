@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import later.Later
import kotlin.js.JsExport

@JsExport
interface Creator<P, R> {
    fun create(params: P): Later<R>
}