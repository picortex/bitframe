@file:JsExport

package pimonitor.evaluation.businesses

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowCreateBusinessForm : BusinessesIntent()
    object ExitDialog : BusinessesIntent()
    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusiness) : BusinessesIntent()
}