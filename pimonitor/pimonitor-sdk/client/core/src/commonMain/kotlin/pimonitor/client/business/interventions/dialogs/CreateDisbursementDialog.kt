package pimonitor.client.business.interventions.dialogs

import pimonitor.client.utils.disbursables.disbursements.forms.CreateDisbursementForm
import pimonitor.core.utils.disbursables.Disbursable
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import pimonitor.client.utils.disbursables.disbursements.fields.DisbursementFields as Fields
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams as Params

class CreateDisbursementDialog(
    disbursable: Disbursable,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(disbursable, params, block))