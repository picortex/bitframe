@file:JsExport

package bitframe.presenters.collections.table

import kotlin.js.JsExport

data class Column<in D>(
    val name: String,
    val accessor: (Row<D>) -> String
)