@file:JsExport

package bitframe.presenters.collections.table

import kotlin.js.JsExport

data class Row<out D>(
    val rowIndex: Int,
    val data: D,
    val selected: Boolean = false
) {
    val rowNumber get() = rowIndex + 1
}