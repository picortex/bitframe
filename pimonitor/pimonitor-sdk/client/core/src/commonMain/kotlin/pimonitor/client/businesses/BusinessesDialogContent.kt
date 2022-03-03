@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")
@file:OptIn(ExperimentalTypeInference::class)

package pimonitor.client.businesses

import pimonitor.client.businesses.forms.CreateBusinessFormFields
import pimonitor.client.businesses.forms.InviteToShareFormFields
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawParams
import presenters.modal.*
import presenters.modal.builders.ConfirmDialogBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.modal.builders.FormDialogBuilder
import presenters.modal.builders.FormDialogBuildingBlock
import presenters.table.Row
import kotlin.experimental.ExperimentalTypeInference
import kotlin.js.JsExport
import kotlin.jvm.JvmField

object BusinessesDialogContent {
    @JvmField
    val CreateBusiness = "Add Business"

    @JvmField
    val InviteToShareReports = "Request information"

    @JvmField
    val Intervene = "Intervene"

    @JvmField
    val CaptureInvestment = "Capture Investment"

    internal fun createBusinessDialog(
        block: FormDialogBuildingBlock<CreateMonitoredBusinessRawParams>
    ) = formDialog(
        heading = CreateBusiness,
        details = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later.",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun inviteToShareReportsDialog(
        businessName: String,
        contactEmail: String,
        block: FormDialogBuildingBlock<InviteToShareReportsRawParams>
    ) = formDialog(
        heading = InviteToShareReports,
        details = "Request $businessName information by email",
        fields = InviteToShareFormFields().copy(contactEmail),
        block
    )

    internal fun <T> interveneDialog(
        monitored: MonitoredBusinessSummary,
        @BuilderInference block: FormDialogBuildingBlock<T>
    ) = formDialog(
        heading = Intervene,
        details = "Perform an intervention to ${monitored.name} pronto",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun <T> captureInvestmentDialog(
        monitored: MonitoredBusinessSummary,
        @BuilderInference block: FormDialogBuildingBlock<T>
    ) = formDialog(
        heading = CaptureInvestment,
        details = "Capturing investment for ${monitored.name}",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun deleteSingleDialog(
        monitored: MonitoredBusinessSummary,
        block: ConfirmDialogBuildingBlock
    ) = confirmDialog(
        heading = "Delete Business",
        details = "Completely delete ${monitored.name} from your list of businesses",
        block
    )

    internal fun deleteManyDialog(
        monitored: Array<Row<MonitoredBusinessSummary>>,
        block: ConfirmDialogBuildingBlock
    ) = confirmDialog(
        heading = "Delete Businesses",
        details = "Completely delete ${monitored.size} from your list of businesses",
        block
    )
}