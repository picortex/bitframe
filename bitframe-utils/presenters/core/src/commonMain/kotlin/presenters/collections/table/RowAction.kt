@file:JsExport

package presenters.collections.table

import kotlin.js.JsExport

data class RowAction<in D>(
    val name: String,
    val handler: (Row<D>) -> Unit
)