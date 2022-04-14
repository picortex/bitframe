package pimonitor.client.business.investments.dialogs

import pimonitor.client.utils.disbursements.forms.CreateDisbursementForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import pimonitor.client.utils.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

class CreateDisbursementDialog(
    investment: String,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(investment, params, block))