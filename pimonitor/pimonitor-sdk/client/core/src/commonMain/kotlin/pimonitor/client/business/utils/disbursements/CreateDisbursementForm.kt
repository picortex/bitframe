package pimonitor.client.business.utils.disbursements

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.business.utils.disbursements.CreateDisbursementFields as Fields
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementRawParams as Params

class CreateDisbursementForm(
    name: String,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for $name",
    fields = Fields(),
    block
)