package pimonitor.client.utils.disbursements.forms

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.utils.disbursements.params.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

fun CreateDisbursementForm(
    name: String,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for $name",
    fields = Fields(params),
    block
)