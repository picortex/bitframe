@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlin.js.JsExport

data class Row<out D>(
    val index: Int,
    val item: D,
    val selected: Boolean = false
) {
    val number get() = index + 1
}