package pimonitor.client.business.interventions.dialogs

import pimonitor.client.business.utils.disbursements.CreateDisbursementForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.utils.disbursements.DisbursementFields as Fields
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams as Params

class CreateDisbursementDialog(
    intervention: String,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(intervention, block))