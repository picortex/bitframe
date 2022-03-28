@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.CaptureInvestmentsRawParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitoredName: String,
    block: FormDialogBuildingBlock<CaptureInvestmentsRawParams>
) : Dialog.Form<CaptureInvestmentFields, CaptureInvestmentsRawParams>(
    heading = "Capture Investments",
    details = "Capturing investment for $monitoredName",
    fields = CaptureInvestmentFields(),
    block
)