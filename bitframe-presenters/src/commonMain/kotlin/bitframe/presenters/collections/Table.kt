@file:JsExport

package bitframe.presenters.collections

import bitframe.presenters.collections.table.Column
import bitframe.presenters.collections.table.Row
import kotlin.js.JsExport

abstract class Table<D>(
    open val columns: List<Column<D>>,
    open val rows: List<Row<D>>,
) {
    companion object {
        fun <D> of(
            columns: List<Column<D>>,
            data: List<D>
        ): Table<D> = TableImpl(columns, List(data.size) { Row(it + 1, data[it]) })
    }

    val columnsArray get() = columns.toTypedArray()
    val rowsArray get() = rows.toTypedArray()
    val data get() = rows.map { it.data }
    val dataArray get() = data.toArray()

    fun <S> mapColumns(transform: (Column<D>) -> Column<S>): Table<S> {
        TODO()
    }

    private fun <E> List<E>.toArray(): Array<E> {
        mapColumns {
            it
        }
        val array = Array<Any?>(size) { null }
        forEachIndexed { index, e -> array[index] = e }
        return array as Array<E>
    }
}