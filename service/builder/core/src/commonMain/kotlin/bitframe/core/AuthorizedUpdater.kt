@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import koncurrent.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface AuthorizedUpdater<in P, out R> {
    @JsName("_ignore_update")
    fun update(params: RequestBody.Authorized<P>): Later<R>
}