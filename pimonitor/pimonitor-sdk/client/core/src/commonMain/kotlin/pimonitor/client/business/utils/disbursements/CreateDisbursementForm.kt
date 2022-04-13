package pimonitor.client.business.utils.disbursements

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.business.utils.disbursements.DisbursementFields as Fields
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams as Params

class CreateDisbursementForm(
    name: String,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for $name",
    fields = Fields(params),
    block
)