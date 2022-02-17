@file:JsExport

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import kotlin.js.JsExport

sealed class BusinessesDialog {

    data class None(val ordinal: Int = 1) : BusinessesDialog()

    data class CreateBusiness(
        override val title: String = "Add Business",
        override val subTitle: String = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later."
    ) : BusinessesDialog(), Dialog

    data class InviteToShareReports(
        override val title: String = "Request information",
        override val subTitle: String = "Request business information by email",
        val monitored: MonitoredBusinessSummary
    ) : BusinessesDialog(), Dialog
}