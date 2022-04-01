@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.dialogs

import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.invites.fields.InviteToShareFormFields as Fields
import pimonitor.core.businesses.params.InviteToShareReportsRawParams as Params

class InviteToShareReportsDialog(
    businessName: String,
    contactEmail: String,
    message: String,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(
    heading = "Request information",
    details = "Request $businessName's information by email",
    fields = Fields(contactEmail, message),
    block
)