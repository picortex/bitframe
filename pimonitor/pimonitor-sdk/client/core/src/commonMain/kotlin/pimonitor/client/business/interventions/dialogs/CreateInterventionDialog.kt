package pimonitor.client.business.interventions.dialogs

import pimonitor.client.business.interventions.forms.CreateInterventionForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.interventions.fields.CreateInterventionFields as Fields
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams as Params

class CreateInterventionDialog(
    business: String,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateInterventionForm(business, block))