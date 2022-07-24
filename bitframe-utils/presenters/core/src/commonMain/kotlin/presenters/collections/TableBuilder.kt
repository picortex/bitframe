package presenters.collections

import presenters.actions.SimpleAction

class TableBuilder<D> {
    //    val actions: MutableList<TableAction<D>> = mutableListOf()
    val populateActions: MutableList<SimpleAction> = mutableListOf()
    val columns: MutableList<Column<D>> = mutableListOf()

    var emptyMessage = "No data found"

    var emptyDetails = "You haven't added any data yet"

    fun emptyAction(name: String, handler: () -> Unit) {
        populateActions += SimpleAction(name, handler)
    }

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

//    fun selectable(name: String = "Select") {
//        columns += Column.Select(name)
//    }

    fun column(name: String, accessor: (Row<D>) -> String) {
        columns += Column.Data(name, accessor)
    }
}