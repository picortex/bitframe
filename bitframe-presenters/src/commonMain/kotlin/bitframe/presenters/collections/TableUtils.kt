package bitframe.presenters.collections

import bitframe.presenters.collections.table.ColumnBuilder
import kotlinx.collections.interoperable.toInteroperableList

fun <D> tableOf(
    data: Collection<D>,
    builder: ColumnBuilder<D>.() -> Unit
): Table<D> = Table.of(
    columns = ColumnBuilder<D>().apply(builder).columns.toInteroperableList(),
    data = data.toList().toInteroperableList()
)