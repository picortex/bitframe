package presenters.cases

import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport
import kotlin.js.JsName

fun <C, D> CentralState<C, D>.copy(table: Table<D>) = CentralState(
    emphasis = Emphasis.None,
    table = table,
    context = context
)

fun <C, D> CentralState<C, D>.copy(
    table: Table<D>,
    context: C?
) = copy(emphasis = Emphasis.None, table = table, context = context)