@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface AuthorizedReader<in P, out R> {
    @JsName("_ignore_load")
    fun load(params: RequestBody.Authorized<P>): Later<R>
}