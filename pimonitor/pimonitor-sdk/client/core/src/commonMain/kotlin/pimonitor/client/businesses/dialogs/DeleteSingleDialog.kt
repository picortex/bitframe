@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.ConfirmDialogBuildingBlock
import kotlin.js.JsExport

class DeleteSingleDialog(
    val monitored: MonitoredBusinessSummary,
    val block: ConfirmDialogBuildingBlock
) : Dialog.Confirm(
    heading = "Delete Business",
    details = "Completely delete ${monitored.name} from your list of businesses",
    block
)