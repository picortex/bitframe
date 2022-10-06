@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import koncurrent.Later
import kotlin.js.JsExport

@JsExport
interface Reader<in P, out R> {
    fun load(params: P): Later<R>
}