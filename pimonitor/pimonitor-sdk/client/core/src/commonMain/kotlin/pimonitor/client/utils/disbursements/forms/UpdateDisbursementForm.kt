package pimonitor.client.utils.disbursements.forms

import identifier.Name
import pimonitor.core.utils.disbursements.Disbursement
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.utils.disbursements.params.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

fun UpdateDisbursementForm(
    disbursement: Disbursement,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Edit Disbursement",
    details = "Edit ${Name(disbursement.by.name).first}'s ${disbursement.amount} disbursement",
    fields = Fields(params, disbursement),
    block
)