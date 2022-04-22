@file:Suppress("ArrayInDataClass")

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import pimonitor.core.interventions.params.InterventionRawParams
import pimonitor.core.investments.params.InvestmentRawParams
import presenters.table.Row

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()

    data class ShowCreateBusinessForm(val params: CreateMonitoredBusinessRawParams?) : BusinessesIntent()
    data class SendCreateBusinessForm(val params: CreateMonitoredBusinessRawParams) : BusinessesIntent()

    data class ShowCreateInterventionForm(val monitored: MonitoredBusinessSummary, val params: InterventionRawParams?) : BusinessesIntent()
    data class SendCreateInterventionForm(val monitored: MonitoredBusinessSummary, val params: InterventionRawParams) : BusinessesIntent()

    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary, val params: InviteToShareReportsRawFormParams?) : BusinessesIntent()
    data class SendInviteToShareReportsForm(val monitored: MonitoredBusinessSummary, val params: InviteToShareReportsRawFormParams) : BusinessesIntent()

    data class ShowCreateInvestmentForm(val monitored: MonitoredBusinessSummary, val params: InvestmentRawParams?) : BusinessesIntent()
    data class SendCreateInvestmentForm(val monitored: MonitoredBusinessSummary, val params: InvestmentRawParams) : BusinessesIntent()

    data class ShowDeleteSingleConfirmationDialog(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<MonitoredBusinessSummary>>) : BusinessesIntent()

    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: Array<MonitoredBusinessSummary>) : BusinessesIntent()
}