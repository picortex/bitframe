package presenters.collections

class ColumnsBuilder<D> {
    //    val actions: MutableList<TableAction<D>> = mutableListOf()
    val columns: MutableList<Column<D>> = mutableListOf()

//    fun primaryAction(name: String, handler: () -> Unit) {
//        actions += TableAction.Primary(name, handler)
//    }
//
//    fun singleAction(name: String, handler: (Row<D>) -> Unit) {
//        actions += TableAction.SingleSelect(name, handler)
//    }

//    fun multiAction(name: String, handler: (Array<Row<D>>) -> Unit) {
//        actions += TableAction.MultiSelect(name, handler)
//    }

    fun selectable(name: String = "Select") {
        columns.add(Column.Select(name) as Column<D>)
    }

    fun column(name: String, accessor: (Row<D>) -> String) {
        columns += Column.Data(name, accessor)
    }
}