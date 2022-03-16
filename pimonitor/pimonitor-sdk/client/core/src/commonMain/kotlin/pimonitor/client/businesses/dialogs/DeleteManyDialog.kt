@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.table.Row
import kotlin.js.JsExport

class DeleteManyDialog(
    val monitored: Array<Row<MonitoredBusinessSummary>>,
    val block: ConfirmDialogBuildingBlock
) : Dialog.Confirm(
    heading = "Delete Businesses",
    details = "Completely delete ${monitored.size} from your list of businesses",
    block
)