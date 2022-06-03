@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import live.*
import presenters.cases.Feedback
import kotlin.js.JsExport
import kotlin.js.JsName

open class Table<D>(
    val live: MutableLive<TableState<D>>
) : Tabular<D> {
    @JsName("from")
    constructor(
        columns: List<Column<D>>,
        rows: List<Row<D>>,
        actions: List<TableAction<D>>
    ) : this(mutableLiveOf(TableState(Feedback.None, columns, rows, actions)))

    fun toggleAllSelected() = changeAllSelection(!areAllRowsSelected)

    fun selectAll() = changeAllSelection(selected = true)

    fun unSelectAll() = changeAllSelection(selected = false)

    fun invertSelection() {
        live.value = live.value.copy(
            rows = live.value.rows.map {
                it.copy(selected = !it.selected)
            }.toInteroperableList()
        )
    }

    fun changeAllSelection(selected: Boolean) {
        live.value = live.value.copy(
            rows = live.value.rows.map {
                it.copy(selected = selected)
            }.toInteroperableList()
        )
    }

    fun changeSelection(row: Row<D>, selected: Boolean? = null) {
        live.value = live.value.copy(
            rows = live.value.rows.map {
                if (it == row) it.copy(selected = selected ?: !row.selected) else it
            }.toInteroperableList()
        )
    }

    @JsName("selectRow")
    fun select(row: Row<D>) = changeSelection(row, selected = true)

    @JsName("selectRowNumber")
    fun select(rowNumber: Int) = select(rows[rowNumber - 1])

    @JsName("unSelectRow")
    fun unSelect(row: Row<D>) = changeSelection(row, selected = false)

    @JsName("unSelectRowNumber")
    fun unSelect(rowNumber: Int) = unSelect(rows[rowNumber - 1])

    val isEmptyTable by lazy { this is EmptyTable }
    val asEmpty by lazy { this as EmptyTable }

    override val columns get() = live.value.columns
    override val rows get() = live.value.rows
    override val isEmpty get() = rows.isEmpty()
    override val areAllRowsSelected get() = rows.all { it.selected }
    override val areNoRowsSelected get() = rows.all { !it.selected }
    override val allSelectedRows get() = rows.filter { it.selected }.toInteroperableList()
    override val areSomeRowsSelected get() = !areNoRowsSelected && rows.size != allSelectedRows.size
    override val rowActions
        get() = columns.filterIsInstance<Column.Action<D>>().flatMap {
            it.actions
        }.toInteroperableList()
    override val actions: List<TableAction<D>> get() = live.value.actions

    companion object {
        fun <D> of(
            columns: List<Column<D>>,
            data: List<D>,
            actions: List<TableAction<D>>
        ): Table<D> = Table(columns, List(data.size) { Row(it, data[it]) }.toInteroperableList(), actions)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is Table<*> -> columns == other.columns && rows == rows
        else -> false
    }

    override fun toString() = "Table(cols=${columns.size},rows=${rows.size})"

    override fun hashCode(): Int = toString().hashCode()
}