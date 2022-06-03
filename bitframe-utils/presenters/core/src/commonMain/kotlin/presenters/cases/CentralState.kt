@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport
import kotlin.js.JsName

class CentralState<out C, D>(
    val emphasis: Emphasis = Emphasis.Loading("Please wait . . ."),
    val table: Table<D> = tableOf(emptyList()) {},
    val context: C? = null,
) {
    @JsName("_ignore_fromLoading")
    constructor(message: String) : this(emphasis = Emphasis.Loading(message))

    val isLoading get() = emphasis.isLoading
    val asLoading get() = emphasis.asLoading

    val isSuccess get() = emphasis.isSuccess
    val asSuccess get() = emphasis.asSuccess

    val isFailure get() = emphasis.isFailure
    val asFailure get() = emphasis.asFailure

    val hasDialog get() = emphasis.isDialog
    val asDialog get() = emphasis.asDialog

    val dialog get() = (emphasis as? Emphasis.Modal)?.dialog

    override fun equals(other: Any?): Boolean = other is CentralState<*, *>
            && emphasis == other.emphasis
            && table == other.table
            && context == other.context

    override fun hashCode(): Int = emphasis.hashCode() + table.hashCode() + context.hashCode()

    override fun toString() = buildString {
        appendLine("CentralState(")
        appendLine("\temphasis=$emphasis,")
        appendLine("\ttable=$table,")
        appendLine("\tcontext=$context")
        appendLine(")")
    }
}