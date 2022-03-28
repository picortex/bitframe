@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.CaptureInvestmentsRawParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitored: MonitoredBusinessSummary,
    block: FormDialogBuildingBlock<CaptureInvestmentsRawParams>
) : Dialog.Form<CaptureInvestmentFields, CaptureInvestmentsRawParams>(
    heading = "Capture Investments",
    details = "Capturing investment for ${monitored.name}",
    fields = CaptureInvestmentFields(),
    block
)