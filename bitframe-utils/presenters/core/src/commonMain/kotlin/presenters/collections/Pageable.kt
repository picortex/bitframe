@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Pageable<out T> {
    val paginator: Paginator<T>
}