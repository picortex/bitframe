package bitframe.presenters.collections

import bitframe.presenters.collections.table.ColumnBuilder

fun <D> tableOf(
    data: Collection<D>,
    builder: ColumnBuilder<D>.() -> Unit
): Table<D> = Table.of(
    columns = ColumnBuilder<D>().apply(builder).columns,
    data = data.toList()
)