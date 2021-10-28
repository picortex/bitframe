package bitframe.presenters.collections.table

class ColumnBuilder<D>(internal val columns: MutableList<Column<D>> = mutableListOf()) {
    fun selectable(name: String = "Select") {
        columns += Column.Select(name)
    }

    fun actions(name: String, builder: ActionsBuilder<D>.() -> Unit) {
        columns += Column.Action(name, ActionsBuilder<D>().apply(builder).actions)
    }

    fun column(name: String, accessor: (Row<D>) -> String) {
        columns += Column.Data(name, accessor)
    }
}