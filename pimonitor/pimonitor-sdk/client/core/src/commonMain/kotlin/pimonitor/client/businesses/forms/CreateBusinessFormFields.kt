@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.forms

import presenters.fields.BooleanInputField
import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams as Params

class CreateBusinessFormFields {
    val businessName = TextInputField(
        name = Params::businessName,
        label = "Business name",
        hint = "PiCortex LLC"
    )
    val contactName = TextInputField(
        name = Params::contactName,
        label = "Contact Name",
        hint = "John Doe"
    )
    val contactEmail = EmailInputField(
        name = Params::contactEmail,
        label = "Contact Email",
        hint = "john@doe.com"
    )
    val sendInvite = BooleanInputField(
        name = Params::sendInvite,
        label = "Ask to share reports?",
        value = true
    )
}