@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import koncurrent.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface AuthorizedDeleter<in P, out R> {
    @JsName("_ignore_delete")
    fun delete(params: RequestBody.Authorized<P>): Later<R>
}