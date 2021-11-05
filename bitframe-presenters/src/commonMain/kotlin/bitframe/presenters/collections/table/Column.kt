@file:JsExport

package bitframe.presenters.collections.table

import kotlin.js.JsExport
import bitframe.presenters.collections.table.Action as TableAction
import kotlinx.collections.interoperable.List

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
        val actions: List<TableAction<D>>
    ) : Column<D>(name)
}