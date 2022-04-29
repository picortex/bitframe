@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Creator<in P, out R> {
    fun create(params: P): Later<R>
}