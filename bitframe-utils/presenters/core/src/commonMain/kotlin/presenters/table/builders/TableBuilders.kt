package presenters.table.builders

import kotlinx.collections.interoperable.toInteroperableList
import presenters.table.EmptyTable
import presenters.table.Table
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <D> tableOf(
    data: Collection<D>,
    block: TableBuilder<D>.() -> Unit
): Table<D> {
    val builder = TableBuilder<D>().apply(block)
    val columns = builder.columns.toInteroperableList()
    val actions = builder.actions.toInteroperableList()
    return if (data.isEmpty()) EmptyTable(
        message = builder.emptyMessage,
        details = builder.emptyDetails,
        populateActions = builder.populateActions.toInteroperableList(),
        columns = columns,
        actions = actions
    ) else Table.of(
        columns = columns,
        data = data.toList().toInteroperableList(),
        actions = actions
    )
}