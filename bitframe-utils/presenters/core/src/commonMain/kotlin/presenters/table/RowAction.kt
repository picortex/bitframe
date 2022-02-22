@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlin.js.JsExport

data class RowAction<in D>(
    val name: String,
    val handler: (Row<D>) -> Unit
)