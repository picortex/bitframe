package pimonitor.client.business.utils.disbursements

import identifier.Name
import pimonitor.core.business.utils.disbursements.Disbursement
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.business.utils.disbursements.DisbursementFields as Fields
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams as Params

class EditDisbursementForm(
    disbursement: Disbursement,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Edit Disbursement",
    details = "Edit ${Name(disbursement.by.name).first}'s ${disbursement.amount} disbursement",
    fields = Fields(params, disbursement),
    block
)