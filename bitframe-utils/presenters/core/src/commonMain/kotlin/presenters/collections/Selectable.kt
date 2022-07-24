@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Selectable<in T> {
    val selector: Selector<T>
}