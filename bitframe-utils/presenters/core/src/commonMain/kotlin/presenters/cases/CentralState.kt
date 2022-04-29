@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport
import kotlin.js.JsName

data class CentralState<C, D>(
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
}