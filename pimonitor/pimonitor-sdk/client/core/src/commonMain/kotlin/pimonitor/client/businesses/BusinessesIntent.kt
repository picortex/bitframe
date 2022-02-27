@file:Suppress("ArrayInDataClass")

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.table.Row
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowCreateBusinessForm : BusinessesIntent()
    object ExitDialog : BusinessesIntent()
    data class ShowInterveneForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowCaptureInvestmentForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowUpdateInvestmentForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteSingleConfirmationDialog(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<MonitoredBusinessSummary>>) : BusinessesIntent()
    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: Array<MonitoredBusinessSummary>) : BusinessesIntent()
    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
}