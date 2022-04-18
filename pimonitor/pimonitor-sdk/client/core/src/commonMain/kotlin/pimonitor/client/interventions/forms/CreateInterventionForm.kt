package pimonitor.client.interventions.forms

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.interventions.fields.InterventionFields as Fields
import pimonitor.core.interventions.params.InterventionRawParams as Params

internal fun CreateInterventionForm(
    businesses: List<MonitoredBusinessSummary>,
    business: MonitoredBusinessSummary?,
    params: Params?,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Create Intervention",
    details = "Capture an intervention",
    fields = Fields(businesses, business, params, null),
    block
)