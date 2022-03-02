package pimonitor.client.businesses.forms

import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import presenters.fields.EmailInputField
import presenters.fields.TextInputField

data class InviteToShareFormFields(
    val to: EmailInputField = EmailInputField(
        name = "to",
        label = "To",
        hint = "john@doe.com"
    ),
    val subject: TextInputField = TextInputField(
        name = "subject",
        label = "Subject",
        hint = "Enter invite subject here",
        value = "Invite to share reports"
    ),
    val message: TextInputField = TextInputField(
        name = "message",
        label = "Message",
        hint = "Enter invite message here",
        value = "We welcome you to invite"
    )
) {
    fun copy(params: CreateMonitoredBusinessRawParams) = copy(
        to = to.copy(value = params.contactEmail),
        message = message.copy(value = "We would like to invite you to share your financial and technical reports with us")
    )
}