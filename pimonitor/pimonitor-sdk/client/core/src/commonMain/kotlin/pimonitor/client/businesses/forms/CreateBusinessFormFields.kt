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

data class CreateBusinessFormFields internal constructor(
    val title: String = "Add a business",
    val businessName: TextInputField = TextInputField(
        name = "businessName",
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    val contactName: TextInputField = TextInputField(
        name = "contactName",
        label = "Contact Name",
        hint = "John Doe"
    ),
    val contactEmail: EmailInputField = EmailInputField(
        name = "contactEmail",
        label = "Contact Email",
        hint = "john@doe.com"
    ),
    val sendInvite: BooleanInputField = BooleanInputField(
        name = "sendInvite",
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