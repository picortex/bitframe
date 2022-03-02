@file:Suppress("ArrayInDataClass")

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawParams
import presenters.table.Row
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ExitDialog : BusinessesIntent()

    // dialogs
    object ShowCreateBusinessForm : BusinessesIntent()
    data class ShowInterveneForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowCaptureInvestmentForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteSingleConfirmationDialog(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<MonitoredBusinessSummary>>) : BusinessesIntent()

    data class SubmitCreateBusinessForm(val params: CreateMonitoredBusinessRawParams) : BusinessesIntent()
    data class SubmitInviteToShareReportsForm(val params: InviteToShareReportsRawParams) : BusinessesIntent()

    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: Array<MonitoredBusinessSummary>) : BusinessesIntent()
}