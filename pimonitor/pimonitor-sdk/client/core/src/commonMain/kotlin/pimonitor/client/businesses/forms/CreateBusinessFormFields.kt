@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.forms

import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import presenters.fields.BooleanInputField
import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams as Params

data class CreateBusinessFormFields internal constructor(
    val title: String = "Add a business",
    val businessName: TextInputField = TextInputField(
        name = Params::businessName.name,
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    val contactName: TextInputField = TextInputField(
        name = Params::contactName.name,
        label = "Contact Name",
        hint = "John Doe"
    ),
    val contactEmail: EmailInputField = EmailInputField(
        name = Params::contactEmail.name,
        label = "Contact Email",
        hint = "john@doe.com"
    ),
    val sendInvite: BooleanInputField = BooleanInputField(
        name = Params::sendInvite.name,
        label = "Ask to share reports",
        value = true
    ),
    val submitButton: ButtonInputField = ButtonInputField(text = "Submit")
) {
    @JsName("_ignore_copyWithParams")
    fun copy(params: CreateMonitoredBusinessRawParams) = copy(
        businessName = businessName.copy(value = params.businessName),
        contactName = contactName.copy(value = params.contactName),
        contactEmail = contactEmail.copy(value = params.contactEmail),
        sendInvite = sendInvite.copy(value = params.sendInvite)
    )
}