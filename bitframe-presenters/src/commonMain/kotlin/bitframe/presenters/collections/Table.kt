@file:JsExport

package bitframe.presenters.collections

import bitframe.presenters.collections.table.Column
import bitframe.presenters.collections.table.Row
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import live.Live
import kotlin.js.JsExport
import kotlin.js.JsName

class Table<D>(
    columns: List<Column<D>>,
    rows: List<Row<D>>,
) {
    data class State<D>(
        val columns: List<Column<D>>,
        val rows: List<Row<D>>
    ) {
        val isEmpty = rows.isEmpty()

        val areAllRowsSelected = rows.all { it.selected }

        val areNoRowsSelected = rows.all { !it.selected }

        val allSelectedRows = rows.filter { it.selected }

        val areSomeRowsSelected = !areNoRowsSelected && rows.size != allSelectedRows.size
    }

    val live = Live(State(columns, rows))

    fun selectAll() {
        live.value = live.value.copy(
            rows = live.value.rows.map {
                it.copy(selected = true)
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

    fun select(row: Row<D>) = changeSelection(row, selected = true)

    @JsName("selectRowNumber")
    fun select(number: Int) = select(rows[number - 1])

    fun unSelect(row: Row<D>) = changeSelection(row, selected = false)

    @JsName("unSelectRowNumber")
    fun unSelect(number: Int) = unSelect(rows[number - 1])

    val columns get() = live.value.columns
    val rows get() = live.value.rows
    val isEmpty get() = live.value.isEmpty

    val areAllRowsSelected get() = live.value.areAllRowsSelected

    val areNoRowsSelected get() = live.value.areNoRowsSelected

    val allSelectedRows get() = live.value.allSelectedRows

    val areSomeRowsSelected get() = live.value.areSomeRowsSelected

    companion object {
        fun <D> of(
            columns: List<Column<D>>,
            data: List<D>
        ): Table<D> = Table(columns, List(data.size) { Row(it, data[it]) }.toInteroperableList())
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is Table<*> -> columns == other.columns
        else -> false
    }

    override fun toString(): String {
        return "Table(columns=${columns.size},rows=${rows.size})"
    }

    override fun hashCode(): Int {
        return columns.hashCode()
    }
}