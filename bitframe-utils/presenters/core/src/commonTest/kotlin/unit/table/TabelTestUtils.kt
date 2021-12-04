package unit.table

import presenters.collections.Table
import presenters.collections.table.Column

fun <D> print(table: Table<D>) {
    table.columns.forEach {
        if (it is Column.Select) {
            val middle = if (table.areAllRowsSelected) {
                "x"
            } else if (table.areNoRowsSelected) {
                " "
            } else {
                "-"
            }
            print("[$middle]\t")
        } else {
            print(it.name + "\t")
        }
        if (it.name == "Name") {
            print("\t\t")
        }
    }
    println()
    table.columns.forEach {
        print(it.name.toCharArray().joinToString(separator = "") { "-" } + "\t")
        if (it.name == "Name") {
            print("\t\t")
        }
    }
    println()
    table.rows.forEach { row ->
        table.columns.forEach { col ->
            when (col) {
                is Column.Select -> print((if (row.selected) "[x]" else "[ ]") + "\t")
                is Column.Data -> print(col.accessor(row) + "\t")
                is Column.Action -> print(col.actions.joinToString(separator = "|") { it.name })
            }
        }
        println()
    }
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