@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.CreateInvestmentsRawParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitored: MonitoredBusinessSummary,
    block: FormDialogBuildingBlock<CreateInvestmentsRawParams>
) : Dialog.Form<CaptureInvestmentFields, CreateInvestmentsRawParams>(
    heading = "Capture Investments",
    details = "Capturing investment for ${monitored.name}",
    fields = CaptureInvestmentFields(),
    block
)