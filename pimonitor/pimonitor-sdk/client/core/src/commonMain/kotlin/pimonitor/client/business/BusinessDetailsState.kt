@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.dashboards.OperationalDashboard
import presenters.feedbacks.Feedback
import kotlin.js.JsExport

data class BusinessDetailsState(
    val status: Feedback = Feedback.None,
    val business: MonitoredBusinessSummary? = null,
    val operationDashboard: OperationalDashboard? = null,
    val incomeStatement: IncomeStatement? = null,
    val balanceSheet: BalanceSheet? = null
)

sealed class State(
    val status: Feedback = Feedback.Loading("Loading details, please wait"),
)