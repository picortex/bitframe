@file:Suppress("ArrayInDataClass")

package pimonitor.client.businesses

import pimonitor.core.business.investments.CreateInvestmentsRawParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import presenters.table.Row

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ExitDialog : BusinessesIntent()

    // dialogs
    object ShowCreateBusinessForm : BusinessesIntent()
    data class SendCreateBusinessForm(val params: CreateMonitoredBusinessRawParams) : BusinessesIntent()

    data class ShowInterveneForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()

    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class SendInviteToShareReportsForm(val params: InviteToShareReportsRawFormParams) : BusinessesIntent()

    data class ShowCaptureInvestmentForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class SendCaptureInvestmentForm(val params: CreateInvestmentsRawParams) : BusinessesIntent()

    data class ShowDeleteSingleConfirmationDialog(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<MonitoredBusinessSummary>>) : BusinessesIntent()

    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: Array<MonitoredBusinessSummary>) : BusinessesIntent()
}