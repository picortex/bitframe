@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.business.investments.CaptureInvestmentFields
import pimonitor.core.business.investments.params.CreateInvestmentsRawParams
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitored: MonitoredBusinessSummary,
    block: FormDialogBuildingBlock<CreateInvestmentsRawParamsContextual>
) : Dialog.Form<CaptureInvestmentFields, CreateInvestmentsRawParamsContextual>(
    heading = "Capture Investments",
    details = "Capturing investment for ${monitored.name}",
    fields = CaptureInvestmentFields(),
    block
)