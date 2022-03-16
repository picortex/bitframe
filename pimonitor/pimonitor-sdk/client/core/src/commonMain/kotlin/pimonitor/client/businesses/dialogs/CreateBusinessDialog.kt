@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.businesses.forms.CreateBusinessFormFields as Fields
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams as Params

class CreateBusinessDialog(block: FormDialogBuildingBlock<Params>) : Dialog.Form<Fields, Params>(
    heading = "Add Business",
    details = "Adding a new business to PiMonitor lets you monitor all its operational and financial data in one place. You can always add more details later.",
    fields = Fields(),
    block
)