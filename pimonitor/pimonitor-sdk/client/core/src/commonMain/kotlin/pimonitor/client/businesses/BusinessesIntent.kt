@file:Suppress("ArrayInDataClass")

package pimonitor.client.businesses

import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import pimonitor.core.investments.params.InvestmentRawParams
import presenters.table.Row

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()

    // dialogs
    data class ShowCreateBusinessForm(val params: CreateMonitoredBusinessRawParams?) : BusinessesIntent()
    data class SendCreateBusinessForm(val params: CreateMonitoredBusinessRawParams) : BusinessesIntent()

    data class ShowInterveneForm(val monitored: MonitoredBusinessSummary, val params: CreateInterventionRawFormParams?) : BusinessesIntent()
    data class SendInterveneForm(val monitored: MonitoredBusinessSummary, val params: CreateInterventionRawFormParams) : BusinessesIntent()

    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary, val params: InviteToShareReportsRawFormParams?) : BusinessesIntent()
    data class SendInviteToShareReportsForm(val monitored: MonitoredBusinessSummary, val params: InviteToShareReportsRawFormParams) : BusinessesIntent()

    data class ShowCaptureInvestmentForm(val monitored: MonitoredBusinessSummary, val params: InvestmentRawParams?) : BusinessesIntent()
    data class SendCaptureInvestmentForm(val monitored: MonitoredBusinessSummary, val params: InvestmentRawParams) : BusinessesIntent()

    data class ShowDeleteSingleConfirmationDialog(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<MonitoredBusinessSummary>>) : BusinessesIntent()

    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: Array<MonitoredBusinessSummary>) : BusinessesIntent()
}