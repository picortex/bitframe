package pimonitor.client.business.interventions.dialogs

import pimonitor.client.business.interventions.fields.CreateGoalFields
import pimonitor.client.business.interventions.forms.CreateGoalForm
import pimonitor.client.business.interventions.params.CreateGoalRawFormParams
import pimonitor.core.business.interventions.params.CreateGoalParams
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog

class CreateGoalDialog(
    intervention: String,
    block: FormActionsBuildingBlock<CreateGoalRawFormParams>
) : FormDialog<CreateGoalFields, CreateGoalRawFormParams>(CreateGoalForm(intervention, block))