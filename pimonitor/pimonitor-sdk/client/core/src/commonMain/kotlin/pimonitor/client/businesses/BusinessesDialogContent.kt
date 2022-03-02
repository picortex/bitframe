@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")
@file:OptIn(ExperimentalTypeInference::class)

package pimonitor.client.businesses

import pimonitor.client.businesses.forms.CreateBusinessFormFields
import pimonitor.client.businesses.forms.InviteToShareFormFields
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import presenters.modal.ConfirmAction
import presenters.modal.SubmitAction
import presenters.modal.builders.ConfirmDialogBuilder
import presenters.modal.builders.FormDialogBuilder
import presenters.modal.confirmDialog
import presenters.modal.formDialog
import presenters.table.Row
import kotlin.experimental.ExperimentalTypeInference
import kotlin.js.JsExport
import kotlin.jvm.JvmField

object BusinessesDialogContent {
    @JvmField
    val Confirm = presenters.modal.Confirm

    @JvmField
    val CreateBusiness = "Add Business"

    @JvmField
    val InviteToShareReports = "Request information"

    @JvmField
    val Intervene = "Intervene"

    @JvmField
    val CaptureInvestment = "Capture Investment"

    internal fun <T> createBusinessDialog(
        @BuilderInference block: FormDialogBuilder<T>.() -> SubmitAction<T>
    ) = formDialog(
        heading = CreateBusiness,
        details = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later.",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun <T> inviteToShareDialog(
        monitored: String,
        @BuilderInference block: FormDialogBuilder<T>.() -> SubmitAction<T>
    ) = formDialog(
        heading = InviteToShareReports,
        details = "Request $monitored information by email",
        fields = InviteToShareFormFields(),
        block
    )

    internal fun <T> inviteToShareDialog(
        params: CreateMonitoredBusinessRawParams,
        @BuilderInference block: FormDialogBuilder<T>.() -> SubmitAction<T>
    ) = formDialog(
        heading = InviteToShareReports,
        details = "Request ${params.businessName} information by email",
        fields = InviteToShareFormFields().copy(params),
        block
    )

    internal fun <T> interveneDialog(
        monitored: MonitoredBusinessSummary,
        @BuilderInference block: FormDialogBuilder<T>.() -> SubmitAction<T>
    ) = formDialog(
        heading = Intervene,
        details = "Perform an intervention to ${monitored.name} pronto",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun <T> captureInvestmentDialog(
        monitored: MonitoredBusinessSummary,
        @BuilderInference block: FormDialogBuilder<T>.() -> SubmitAction<T>
    ) = formDialog(
        heading = CaptureInvestment,
        details = "Capturing investment for ${monitored.name}",
        fields = CreateBusinessFormFields(),
        block
    )

    internal fun deleteSingleDialog(
        monitored: MonitoredBusinessSummary,
        block: ConfirmDialogBuilder.() -> ConfirmAction
    ) = confirmDialog(
        heading = "Delete Business",
        details = "Completely delete ${monitored.name} from your list of businesses",
        block
    )

    internal fun deleteManyDialog(
        monitored: Array<Row<MonitoredBusinessSummary>>,
        block: ConfirmDialogBuilder.() -> ConfirmAction
    ) = confirmDialog(
        heading = "Delete Businesses",
        details = "Completely delete ${monitored.size} from your list of businesses",
        block
    )
}