@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import later.Later
import kotlin.js.JsExport

@JsExport
interface Updater<in P, out R> {
    fun update(params: P): Later<R>
}