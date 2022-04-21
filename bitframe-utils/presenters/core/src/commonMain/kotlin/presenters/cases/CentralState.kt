@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport
import kotlin.js.JsName

class CentralState<out C, T> private constructor(
    val emphasis: Emphasis = Emphasis.Loading("Please wait . . ."),
    private val _context: C?,
    val table: Table<T> = tableOf(emptyList()) {},
) {
    @JsName("_ignore_fromLoading")
    constructor(message: String) : this(emphasis = Emphasis.Loading(message))

    @JsName("_ignore_from")
    constructor(emphasis: Emphasis, table: Table<T> = tableOf(emptyList()) {}) : this(emphasis, null, table)

    val context: C
        get() = _context as C

    @JsName("_ignore_copy")
    fun copy(table: Table<T>) = copy(emphasis = Emphasis.None, table = table)

    fun copy(
        emphasis: Emphasis = this.emphasis,
        context: @UnsafeVariance C = this.context,
        table: Table<T> = this.table
    ): CentralState<C, T> = CentralState(emphasis, context, table)

    val isLoading get() = emphasis.isLoading
    val asLoading get() = emphasis.asLoading

    val isSuccess get() = emphasis.isSuccess
    val asSuccess get() = emphasis.asSuccess

    val isFailure get() = emphasis.isFailure
    val asFailure get() = emphasis.asFailure

    val hasDialog get() = emphasis.isDialog
    val asDialog get() = emphasis.asDialog

    val dialog get() = (emphasis as? Emphasis.Modal)?.dialog

    override fun toString() = "CentralState(emphasis=$emphasis,context=$_context,table=$table)"
    override fun hashCode() = emphasis.hashCode() or (_context?.hashCode() ?: 0) or table.hashCode()
    override fun equals(other: Any?): Boolean = other is CentralState<*, *>
            && other.emphasis == emphasis
            && other._context == _context
            && other.table == other.table
}