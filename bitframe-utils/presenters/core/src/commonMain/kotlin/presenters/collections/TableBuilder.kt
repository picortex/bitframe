package presenters.collections

import presenters.collections.table.ColumnBuilder
import kotlinx.collections.interoperable.toInteroperableList
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <D> tableOf(
    data: Collection<D>,
    builder: ColumnBuilder<D>.() -> Unit
): Table<D> = Table.of(
    columns = ColumnBuilder<D>().apply(builder).columns.toInteroperableList(),
    data = data.toList().toInteroperableList()
)