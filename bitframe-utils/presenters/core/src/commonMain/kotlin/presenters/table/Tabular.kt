@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

@JsExport
interface Tabular<D> {
    val actions: List<TableAction<D>>

    val rows: List<Row<D>>

    val columns: List<Column<D>>

    val isEmpty: Boolean

    val areAllRowsSelected: Boolean

    val areNoRowsSelected: Boolean

    val allSelectedRows: List<Row<D>>

    val areSomeRowsSelected get() = !areNoRowsSelected && rows.size != allSelectedRows.size

    val rowActions: List<RowAction<D>>
}