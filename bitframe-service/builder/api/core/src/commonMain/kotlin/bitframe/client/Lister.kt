@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport

@JsExport
interface Lister<P, R> {
    fun all(params: P): Later<List<R>>
}