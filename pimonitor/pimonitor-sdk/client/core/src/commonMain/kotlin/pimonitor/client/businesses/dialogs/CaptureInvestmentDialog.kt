@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.business.investments.forms.CaptureInvestmentForm
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.investments.fields.CaptureInvestmentFields as Fields
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual as Params

class CaptureInvestmentDialog(
    monitored: MonitoredBusinessSummary,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CaptureInvestmentForm(monitored.name, block))