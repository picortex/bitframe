@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.interventions.fields.CreateInterventionFields as Fields
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams as Params

class InterveneDialog(
    monitored: MonitoredBusinessSummary,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(
    heading = "Intervene",
    details = "Perform an intervention to ${monitored.name} pronto",
    fields = Fields(),
    block
)