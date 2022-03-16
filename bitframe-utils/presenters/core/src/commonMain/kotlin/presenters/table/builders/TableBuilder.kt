package presenters.table.builders

import kotlinx.collections.interoperable.toInteroperableList
import presenters.table.*
import kotlin.jvm.JvmSynthetic

class TableBuilder<D> {
    internal val actions: MutableList<TableAction<D>> = mutableListOf()
    internal val columns: MutableList<Column<D>> = mutableListOf()

    fun primaryAction(name: String, handler: () -> Unit) {
        actions += TableAction.Primary(name, handler)
    }

    fun singleAction(name: String, handler: (Row<D>) -> Unit) {
        actions += TableAction.SingleSelect(name, handler)
    }

    fun multiAction(name: String, handler: (Array<Row<D>>) -> Unit) {
        actions += TableAction.MultiSelect(name, handler)
    }

    fun selectable(name: String = "Select") {
        columns += Column.Select(name)
    }

    fun actionsColumn(name: String, builder: RowActionsBuilder<D>.() -> Unit) {
        columns += Column.Action(name, RowActionsBuilder<D>().apply(builder).actions)
    }

    fun column(name: String, accessor: (Row<D>) -> String) {
        columns += Column.Data(name, accessor)
    }
}

@JvmSynthetic
fun <D> tableOf(
    data: Collection<D>,
    block: TableBuilder<D>.() -> Unit
): Table<D> {
    val builder = TableBuilder<D>().apply(block)
    val columns = builder.columns.toInteroperableList()
    val actions = builder.actions.toInteroperableList()
    return if (data.isEmpty()) EmptyTable(
        columns = columns,
        actions = actions
    ) else Table.of(
        columns = columns,
        data = data.toList().toInteroperableList(),
        actions = actions
    )
}