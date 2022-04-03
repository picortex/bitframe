package pimonitor.client.business.investments.forms

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.business.investments.fields.CaptureInvestmentFields as Fields
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual as Params

class CaptureInvestmentForm(
    business: String,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Capture Investments",
    details = "Capture investment for $business",
    fields = Fields(),
    block
)