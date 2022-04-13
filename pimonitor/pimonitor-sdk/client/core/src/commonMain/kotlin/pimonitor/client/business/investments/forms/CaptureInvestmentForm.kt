package pimonitor.client.business.investments.forms

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.investments.fields.InvestmentFields as Fields
import pimonitor.client.business.investments.params.InvestmentsRawParams as Params

class CaptureInvestmentForm(
    business: String,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Capture Investments",
    details = "Capture investment for $business",
    fields = Fields(),
    block
)