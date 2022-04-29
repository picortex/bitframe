@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import later.Later
import kotlin.js.JsExport

@JsExport
interface Reader<in P, out R> {
    fun load(params: P): Later<R>
}