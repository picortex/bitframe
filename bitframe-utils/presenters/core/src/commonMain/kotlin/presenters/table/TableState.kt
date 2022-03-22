@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.toInteroperableList
import presenters.cases.Feedback
import kotlin.js.JsExport

data class TableState<D>(
    val status: Feedback = Feedback.None,
    override val columns: List<Column<D>> = emptyList(),
    override val rows: List<Row<D>> = emptyList(),
    override val actions: List<TableAction<D>> = emptyList()
) : TableLike<D> {
    override val isEmpty get() = rows.isEmpty()
    override val areAllRowsSelected get() = rows.all { it.selected }
    override val areNoRowsSelected get() = rows.all { !it.selected }
    override val allSelectedRows get() = rows.filter { it.selected }.toInteroperableList()
    override val areSomeRowsSelected get() = !areNoRowsSelected && rows.size != allSelectedRows.size
    override val rowActions
        get() = columns.filterIsInstance<Column.Action<D>>().flatMap {
            it.actions
        }.toInteroperableList()
}