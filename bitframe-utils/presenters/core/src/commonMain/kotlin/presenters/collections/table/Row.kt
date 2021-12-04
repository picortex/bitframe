@file:JsExport

package presenters.collections.table

import kotlin.js.JsExport

data class Row<out D>(
    val index: Int,
    val data: D,
    val selected: Boolean = false
) {
    val number get() = index + 1
}