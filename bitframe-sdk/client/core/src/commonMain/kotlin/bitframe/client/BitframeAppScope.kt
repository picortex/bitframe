@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import kotlin.js.JsExport

@JsExport
@Deprecated("Use AppScope instead", replaceWith = ReplaceWith("AppScope", "bitframe.AppScope"))
interface BitframeAppScope<A> {
    val api: A
}