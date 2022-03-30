@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import akkounts.reports.FinancialReport
import kotlinx.collections.interoperable.List
import pimonitor.core.invites.InfoResults
import presenters.cases.Feedback
import kotlin.js.JsExport

data class BusinessFinancialsState(
    val status: Feedback = Feedback.Loading(DEFAULT_LOADING_MESSAGE),
    val availableReports: InfoResults<List<String>>? = null,
    val report: FinancialReport? = null
) {
    companion object {
        val DEFAULT_LOADING_MESSAGE = "Loading available reports, please wait . . ."
    }
}