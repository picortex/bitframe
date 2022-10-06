@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import koncurrent.Later
import kotlin.js.JsExport

@JsExport
interface Creator<in P, out R> {
    fun create(params: P): Later<R>
}