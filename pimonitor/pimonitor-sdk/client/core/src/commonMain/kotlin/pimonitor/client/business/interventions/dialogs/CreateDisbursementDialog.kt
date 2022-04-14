package pimonitor.client.business.interventions.dialogs

import pimonitor.client.utils.disbursements.forms.CreateDisbursementForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import pimonitor.client.utils.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

class CreateDisbursementDialog(
    intervention: String,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(intervention, params, block))