@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.businesses.forms.CreateBusinessFormFields
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport

class InterveneDialog(
    monitored: MonitoredBusinessSummary,
    block: FormActionsBuildingBlock<Any>
) : FormDialog<Any, Any>(
    heading = "Intervene",
    details = "Perform an intervention to ${monitored.name} pronto",
    fields = CreateBusinessFormFields(),
    block
)