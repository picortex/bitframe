package pimonitor.client.interventions.forms

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.interventions.InterventionSummary
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.interventions.fields.InterventionFields as Fields
import pimonitor.core.interventions.params.InterventionRawParams as Params

internal fun UpdateInterventionForm(
    businesses: List<MonitoredBusinessSummary>,
    business: MonitoredBusinessSummary?,
    params: Params?,
    intervention: InterventionSummary,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Update Intervention",
    details = "Edit ${intervention.name} intervention",
    fields = Fields(businesses, business, params, intervention),
    block
)