@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.forms

import presenters.fields.BooleanInputField
import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams as Params

data class CreateBusinessFormFields internal constructor(
    val title: String = "Add a business",
    val businessName: TextInputField = TextInputField(
        name = Params::businessName,
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    val contactName: TextInputField = TextInputField(
        name = Params::contactName,
        label = "Contact Name",
        hint = "John Doe"
    ),
    val contactEmail: EmailInputField = EmailInputField(
        name = Params::contactEmail,
        label = "Contact Email",
        hint = "john@doe.com"
    ),
    val sendInvite: BooleanInputField = BooleanInputField(
        name = Params::sendInvite,
        label = "Ask to share reports",
        value = true
    ),
    val submitButton: ButtonInputField = ButtonInputField(text = "Submit")
)