package pimonitor.client.business.interventions.forms

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.business.interventions.fields.CreateInterventionFields as Fields
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams as Params

class CreateInterventionForm(
    val business: String,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Create Intervention",
    details = "Intervene $business",
    fields = Fields(),
    block
)