@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import pimonitor.client.businesses.forms.CreateBusinessFormFields
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class InterveneDialog(
    val monitored: MonitoredBusinessSummary,
    val block: FormDialogBuildingBlock<Any>
) : Dialog.Form<Any, Any>(
    heading = "Intervene",
    details = "Perform an intervention to ${monitored.name} pronto",
    fields = CreateBusinessFormFields(),
    block
)