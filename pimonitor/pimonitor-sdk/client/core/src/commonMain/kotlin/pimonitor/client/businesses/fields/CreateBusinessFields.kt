@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.fields

import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import presenters.fields.BooleanInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams as Params

class CreateBusinessFields(params: CreateMonitoredBusinessRawParams?) {
    val businessName = TextInputField(
        name = Params::businessName,
        label = "Business name",
        hint = "PiCortex LLC",
        value = params?.businessName
    )
    val contactName = TextInputField(
        name = Params::contactName,
        label = "Contact Name",
        hint = "John Doe",
        value = params?.contactName
    )
    val contactEmail = EmailInputField(
        name = Params::contactEmail,
        label = "Contact Email",
        hint = "john@doe.com",
        value = params?.contactEmail
    )
    val sendInvite = BooleanInputField(
        name = Params::sendInvite,
        label = "Ask to share reports?",
        value = params?.sendInvite ?: true
    )
}