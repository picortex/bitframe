@file:JsExport

package pimonitor.evaluation.businesses

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesDialog {

    data class None(val ordinal: Int = 1) : BusinessesDialog()

    data class CreateBusiness(
        val title: String = "Add Business",
        val subTitle: String = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later."
    ) : BusinessesDialog()

    data class InviteToShareReports(
        val title: String = "Request information",
        val subTitle: String = "Request business information by email",
        val monitored: MonitoredBusiness
    ) : BusinessesDialog()
}