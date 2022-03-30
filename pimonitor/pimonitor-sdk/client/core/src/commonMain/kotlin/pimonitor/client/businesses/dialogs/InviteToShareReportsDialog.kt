@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.invites.fields.InviteToShareFormFields as Fields
import pimonitor.core.businesses.params.InviteToShareReportsRawParams as Params

class InviteToShareReportsDialog(
    businessName: String,
    contactEmail: String,
    message: String,
    block: FormDialogBuildingBlock<Params>
) : Dialog.Form<Fields, Params>(
    heading = "Request information",
    details = "Request $businessName's information by email",
    fields = Fields().copy(contactEmail, message),
    block
)