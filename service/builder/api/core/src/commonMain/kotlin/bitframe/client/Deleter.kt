@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import koncurrent.Later
import kotlin.js.JsExport

@JsExport
interface Deleter<in P, out R> {
    fun delete(params: P): Later<R>
}