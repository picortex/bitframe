@file:JsExport

package presenters.collections.table

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class Column<in D>(open val name: String) {
    data class Select<in D>(
        override val name: String,
    ) : Column<D>(name)

    data class Data<in D>(
        override val name: String,
        val accessor: (Row<D>) -> String
    ) : Column<D>(name)

    data class Action<in D>(
        override val name: String,
        val actions: List<RowAction<D>>
    ) : Column<D>(name)
}