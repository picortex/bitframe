package pimonitor.client.interventions.forms

import pimonitor.client.interventions.fields.GoalFields
import pimonitor.client.interventions.params.GoalRawParams
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock

internal fun CreateGoalForm(
    intervention: String,
    params: GoalRawParams?,
    block: FormActionsBuildingBlock<GoalRawParams>
) = Form(
    heading = "Create a goal",
    details = "Create a goal for $intervention",
    fields = GoalFields(params, null),
    block
)