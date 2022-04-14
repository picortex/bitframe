@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "FunctionName")

package pimonitor.client.businesses.dialogs

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.businesses.forms.CreateBusinessFormFields as Fields
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams as Params

fun CreateBusinessForm(
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Add Business",
    details = "Adding a new business to PiMonitor",
    fields = Fields(),
    block
)