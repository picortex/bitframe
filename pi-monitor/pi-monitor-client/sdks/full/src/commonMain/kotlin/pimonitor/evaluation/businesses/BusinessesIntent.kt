@file:JsExport

package pimonitor.evaluation.businesses

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowBusinessForm : BusinessesIntent()
    data class InviteToShareReports(val monitored: MonitoredBusiness) : BusinessesIntent()
}