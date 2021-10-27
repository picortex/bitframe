package bitframe.presenters.collections.table

class ColumnBuilder<D>(internal val columns: MutableList<Column<D>> = mutableListOf()) {
    fun column(name: String, accessor: (Row<D>) -> String) {
        columns += Column(name, accessor)
    }

    fun col(name: String, accessor: (Row<D>) -> String) = column(name, accessor)
}