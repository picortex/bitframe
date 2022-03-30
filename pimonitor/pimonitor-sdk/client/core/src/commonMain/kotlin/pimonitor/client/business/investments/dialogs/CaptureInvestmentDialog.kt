@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.investments.fields.CaptureInvestmentFields
import pimonitor.client.business.investments.forms.CaptureInvestmentForm
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport

class CaptureInvestmentDialog(
    monitoredName: String,
    block: FormActionsBuildingBlock<CreateInvestmentsRawParamsContextual>
) : FormDialog<CaptureInvestmentFields, CreateInvestmentsRawParamsContextual>(CaptureInvestmentForm(monitoredName, block))