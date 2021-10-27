package bitframe.presenters.collections

import bitframe.presenters.collections.table.Column
import bitframe.presenters.collections.table.ColumnBuilder
import bitframe.presenters.collections.table.Row

internal data class TableImpl<D>(
    override val columns: List<Column<D>>,
    override val rows: List<Row<D>>
) : Table<D>(columns, rows)

fun <D> tableOf(
    data: Collection<D>,
    builder: ColumnBuilder<D>.() -> Unit
): Table<D> = Table.of(
    columns = ColumnBuilder<D>().apply(builder).columns,
    data = data.toList()
)