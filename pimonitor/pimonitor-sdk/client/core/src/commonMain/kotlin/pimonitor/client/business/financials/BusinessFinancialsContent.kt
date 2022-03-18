@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import akkounts.reports.FinancialReport
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class BusinessFinancialsContent {
    abstract val availableReports: List<String>

    data class None(
        override val availableReports: List<String>
    ) : BusinessFinancialsContent()

    data class Report(
        override val availableReports: List<String>,
        val data: FinancialReport
    ) : BusinessFinancialsContent()
}