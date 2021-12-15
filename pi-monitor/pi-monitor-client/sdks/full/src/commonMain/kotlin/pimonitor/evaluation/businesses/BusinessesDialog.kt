@file:JsExport

package pimonitor.evaluation.businesses

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesDialog {
    data class None(val ordinal: Int = 1) : BusinessesDialog()
    data class CreateBusiness(val ordinal: Int = 2) : BusinessesDialog()
    data class InviteToShareReports(val monitored: MonitoredBusiness) : BusinessesDialog()
}