@file:JsExport

package pimonitor.client.businesses

import pimonitor.core.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowCreateBusinessForm : BusinessesIntent()
    object ExitDialog : BusinessesIntent()
    data class ShowInviteToShareReportsForm(val monitored: MonitoredBusiness) : BusinessesIntent()
}