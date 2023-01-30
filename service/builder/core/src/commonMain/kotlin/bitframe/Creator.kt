@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import koncurrent.Later
import kotlin.js.JsExport

interface Creator<in P, out R> {
    fun create(params: P): Later<R>
}