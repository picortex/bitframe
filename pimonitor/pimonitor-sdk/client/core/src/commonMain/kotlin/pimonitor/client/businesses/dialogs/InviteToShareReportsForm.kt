@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "FunctionName")

package pimonitor.client.businesses.dialogs

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.invites.fields.InviteToShareFormFields as Fields
import pimonitor.core.businesses.params.InviteToShareReportsRawParams as Params

internal fun InviteToShareReportsForm(
    businessName: String,
    contactEmail: String,
    message: String,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Request information",
    details = "Request $businessName's information by email",
    fields = Fields(contactEmail, message),
    block
)