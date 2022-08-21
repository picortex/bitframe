@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import koncurrent.Later
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface AuthorizedLister<in P, out R> {
    @JsName("_ignore_all")
    fun all(params: RequestBody.Authorized<P>): Later<List<R>>
}