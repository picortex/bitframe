package pimonitor.client.business.interventions.dialogs

import pimonitor.client.utils.disbursements.forms.CreateDisbursementForm
import pimonitor.core.utils.disbursements.Disbursable
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import pimonitor.client.utils.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

class CreateDisbursementDialog(
    disbursable: Disbursable,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(disbursable, params, block))