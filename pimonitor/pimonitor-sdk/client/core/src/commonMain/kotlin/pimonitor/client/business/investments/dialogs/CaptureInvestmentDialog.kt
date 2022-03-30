@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitoredName: String,
    block: FormActionsBuildingBlock<CreateInvestmentsRawParamsContextual>
) : FormDialog<CaptureInvestmentFields, CreateInvestmentsRawParamsContextual>(
    heading = "Capture Investments",
    details = "Capturing investment for $monitoredName",
    fields = CaptureInvestmentFields(),
    block
)