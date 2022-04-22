@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import later.Later
import kotlin.js.JsExport

@JsExport
interface Deleter<P, R> {
    fun delete(params: P): Later<R>
}