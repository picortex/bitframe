@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import kotlinx.collections.interoperable.List
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowCreateBusinessForm : BusinessesIntent()
    object ExitDialog : BusinessesIntent()
    data class Intervene(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class CaptureInvestment(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class UpdateInvestment(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class Delete(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
    data class DeleteAll(val data: List<MonitoredBusinessSummary>) : BusinessesIntent()
    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusinessSummary) : BusinessesIntent()
}