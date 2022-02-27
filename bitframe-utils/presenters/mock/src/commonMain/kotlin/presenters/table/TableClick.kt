package presenters.table

fun <D> Table<D>.action(actionName: String): TableAction<D> {
    TODO("Write an implementation om how to click a global table action")
}

fun <D> Table<D>.click(actionName: String, rowNumber: Int) {
    val actions = columns.filterIsInstance<Column.Action<D>>().flatMap { it.actions }
    val action = actions.firstOrNull { it.name == actionName } ?: run {
        error(
            """
            Action $actionName not found
            
            Available Actions: ${actions.joinToString { it.name }}
            
            
            """.trimIndent()
        )
    }
    val row = rows[rowNumber - 1]
    action.handler(row)
}