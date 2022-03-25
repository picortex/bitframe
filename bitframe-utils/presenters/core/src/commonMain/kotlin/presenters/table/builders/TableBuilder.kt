package presenters.table.builders

import presenters.actions.SimpleAction
import presenters.table.Column
import presenters.table.Row
import presenters.table.TableAction

class TableBuilder<D> {
    internal val actions: MutableList<TableAction<D>> = mutableListOf()
    internal val populateActions: MutableList<SimpleAction> = mutableListOf()
    internal val columns: MutableList<Column<D>> = mutableListOf()

    var emptyMessage = "No data found"

    var emptyDetails = "You haven't added any data yet"

    fun emptyAction(name: String, handler: () -> Unit) {
        populateActions += SimpleAction(name, handler)
    }

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