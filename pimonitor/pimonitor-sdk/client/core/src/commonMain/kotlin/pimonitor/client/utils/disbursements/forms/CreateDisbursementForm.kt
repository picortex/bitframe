package pimonitor.client.utils.disbursements.forms

import pimonitor.core.utils.disbursements.Disbursable
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.utils.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

internal fun CreateDisbursementForm(
    disbursable: Disbursable,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for ${disbursable.name}",
    fields = Fields(params),
    block
)