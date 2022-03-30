@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.businesses.forms.CreateBusinessFormFields as Fields
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams as Params

class CreateBusinessDialog(block: FormActionsBuildingBlock<Params>) : FormDialog<Fields, Params>(
    heading = "Add Business",
    details = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later.",
    fields = Fields(),
    block
)