package presenters.cases

import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport
import kotlin.js.JsName

fun <C, D> CentralState<C, D>.copy(
    table: Table<D>,
    context: C? = this.context
) = copy(emphasis = Emphasis.None, table = table, context = context)