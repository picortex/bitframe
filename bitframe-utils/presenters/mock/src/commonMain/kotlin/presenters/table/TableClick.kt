package presenters.table

fun <D> Table<D>.action(actionName: String): TableAction<D> {
    val action = actions.firstOrNull {
        it.name == actionName
    } ?: error(
        """
            Action $actionName not found
            
            Available Actions: ${actions.joinToString(separator = ",", prefix = "[", postfix = "]") { it.name }}
            
            
            """.trimIndent()
    )
    when (action) {
        is TableAction.Primary -> action.handler()
        is TableAction.SingleSelect -> action.handler(allSelectedRows.first())
        is TableAction.MultiSelect -> action.handler(allSelectedRows.toTypedArray())
    }
    return action
}

fun <D> Table<D>.click(actionName: String, rowNumber: Int) {
    val actions = columns.filterIsInstance<Column.Action<D>>().flatMap { it.actions }
    val action = actions.firstOrNull { it.name == actionName } ?: run {
        error(
            """
            Action $actionName not found
            
            Available Actions: ${actions.joinToString(separator = ",", prefix = "[", postfix = "]") { it.name }}
            
            
            """.trimIndent()
        )
    }
    val row = rows[rowNumber - 1]
    action.handler(row)
}