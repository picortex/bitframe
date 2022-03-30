@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitored: MonitoredBusinessSummary,
    block: FormActionsBuildingBlock<CreateInvestmentsRawParamsContextual>
) : FormDialog<CaptureInvestmentFields, CreateInvestmentsRawParamsContextual>(
    heading = "Capture Investments",
    details = "Capturing investment for ${monitored.name}",
    fields = CaptureInvestmentFields(),
    block
)