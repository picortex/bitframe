package pimonitor.client.businesses.fields

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.interventions.fields.CreateInterventionFields as Fields
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams as Params

internal fun InterveneForm(
    monitored: MonitoredBusinessSummary,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Intervene",
    details = "Perform an intervention to ${monitored.name} pronto",
    fields = Fields(),
    block
)