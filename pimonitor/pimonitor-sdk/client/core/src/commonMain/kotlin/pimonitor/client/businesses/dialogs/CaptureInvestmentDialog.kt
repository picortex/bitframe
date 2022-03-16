@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.businesses.forms.CaptureInvestmentFields
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    val monitored: MonitoredBusinessSummary,
    val block: FormDialogBuildingBlock<Any>
) : Dialog.Form<CaptureInvestmentFields, Any>(
    heading = "Capture Investments",
    details = "Capturing investment for ${monitored.name}",
    fields = CaptureInvestmentFields(),
    block
)