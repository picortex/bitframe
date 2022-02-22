package presenters.table

fun <D> Table<D>.tabulateToConsole() = println(tabulateToString())

fun <D> Table<D>.tabulateToString() = buildString {
    for (action in actions) when (action) {
        is TableAction.Primary -> append("*${action.name}\t")
        is TableAction.SingleSelect -> if (allSelectedRows.size == 1) append("${action.name}\t") else append("")
        is TableAction.MultiSelect -> if (areNoRowsSelected) append("") else append("${action.name}\t")
    }
    appendLine()
    appendLine()
    columns.forEach {
        if (it is Column.Select) {
            val middle = if (areAllRowsSelected) {
                "x"
            } else if (areNoRowsSelected) {
                " "
            } else {
                "-"
            }
            append("[$middle]\t")
        } else {
            append(it.name + "\t")
        }
        if (it.name == "Name") {
            append("\t\t")
        }
    }
    appendLine()
    appendLine()
    rows.forEach { row ->
        columns.forEach { col ->
            when (col) {
                is Column.Select -> append((if (row.selected) "[x]" else "[ ]") + "\t")
                is Column.Data -> append(col.accessor(row) + "\t")
                is Column.Action -> append(col.actions.joinToString(separator = "|") { it.name })
            }
        }
        appendLine()
    }
}