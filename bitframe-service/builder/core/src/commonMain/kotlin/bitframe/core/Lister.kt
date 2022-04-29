@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport

@JsExport
interface Lister<in P, out R> {
    fun all(params: P): Later<List<R>>
}