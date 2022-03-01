@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.table.Table
import presenters.feedbacks.Feedback
import presenters.modal.Dialog
import presenters.table.builders.tableOf
import kotlin.js.JsExport

data class BusinessesState(
    val status: Feedback = Feedback.Loading("Loading your businesses, please wait . . ."),
    val table: Table<MonitoredBusinessSummary> = tableOf(emptyList()) {},
    val dialog: Dialog<String>? = null
)
