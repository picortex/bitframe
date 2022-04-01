package pimonitor.client.business.interventions.forms

import pimonitor.client.business.interventions.fields.CreateGoalFields
import pimonitor.client.business.interventions.params.CreateGoalRawFormParams
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock

class CreateGoalForm(
    intervention: String,
    block: FormActionsBuildingBlock<CreateGoalRawFormParams>
) : Form<CreateGoalFields, CreateGoalRawFormParams> by Form(
    heading = "Create a goal",
    details = "Create a goal for $intervention",
    fields = CreateGoalFields(),
    block
)