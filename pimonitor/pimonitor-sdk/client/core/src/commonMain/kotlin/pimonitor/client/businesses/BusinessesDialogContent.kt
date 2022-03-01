@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.DialogBuilder
import presenters.modal.dialog
import presenters.table.Row
import kotlin.js.JsExport
import kotlin.jvm.JvmField

object BusinessesDialogContent {
    @JvmField
    val Confirm = presenters.modal.Confirm

    @JvmField
    val CreateBusiness = "DIALOG_CREATE_BUSINESS"

    @JvmField
    val InviteToShareReports = "DIALOG_INVITE_TO_SHARE_REPORTS"

    val CaptureInvestmentDialog = "DIALOG_CAPTURE_INVESTMENT"

    val UpdateInvestmentDialog = "DIALOG_UPDATE_INVESTMENT"

    @JvmField
    val Intervene = "DIALOG_INTERVENE"

    internal fun createBusinessDialog(
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Add Business",
        details = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later.",
        content = CreateBusiness,
        block
    )

    internal fun inviteToShareDialog(
        monitored: MonitoredBusinessSummary,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Request information",
        details = "Request ${monitored.name} business information by email",
        content = InviteToShareReports,
        block
    )

    internal fun interveneDialog(
        monitored: MonitoredBusinessSummary,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Intervene",
        details = "Perform an intervention to ${monitored.name} pronto",
        content = Intervene,
        block
    )

    internal fun updateInvestmentDialog(
        monitored: MonitoredBusinessSummary,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Update Investment",
        details = "Update investment for ${monitored.name}",
        content = CaptureInvestmentDialog,
        block
    )

    internal fun captureInvestmentDialog(
        monitored: MonitoredBusinessSummary,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Capture Investment",
        details = "Capturing investment for ${monitored.name}",
        content = CaptureInvestmentDialog,
        block
    )

    internal fun deleteSingleDialog(
        monitored: MonitoredBusinessSummary,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Delete Business",
        details = "Completely delete ${monitored.name} from your list of businesses",
        content = Confirm,
        block
    )

    internal fun deleteManyDialog(
        monitored: Array<Row<MonitoredBusinessSummary>>,
        block: DialogBuilder.() -> Unit
    ) = dialog(
        heading = "Delete Businesses",
        details = "Completely delete ${monitored.size} from your list of businesses",
        content = Confirm,
        block
    )
}