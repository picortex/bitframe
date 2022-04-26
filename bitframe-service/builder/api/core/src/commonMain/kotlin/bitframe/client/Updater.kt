@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import later.Later
import kotlin.js.JsExport

@JsExport
interface Updater<P, R> {
    fun update(params: P): Later<R>
}