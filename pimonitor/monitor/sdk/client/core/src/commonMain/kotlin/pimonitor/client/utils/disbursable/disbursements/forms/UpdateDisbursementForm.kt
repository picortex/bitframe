package pimonitor.client.utils.disbursable.disbursements.forms

import identifier.Name
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.utils.disbursable.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams as Params

internal fun UpdateDisbursementForm(
    disbursement: Disbursement,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Edit Disbursement",
    details = "Edit ${Name(disbursement.by.name).first}'s ${disbursement.amount} disbursement",
    fields = Fields(params, disbursement),
    block
)