@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import koncurrent.Later
import kollections.List
import kotlin.js.JsExport

@JsExport
interface Lister<in P, out R> {
    fun all(params: P): Later<List<R>>
}