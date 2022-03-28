@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import presenters.modal.Dialog
import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport

data class CrowdState<T>(
    val status: Feedback = Feedback.Loading("Please wait . . ."),
    val table: Table<T> = tableOf(emptyList()) {},
    val focus: T? = null,
    val dialog: Dialog<*, *>? = null
)