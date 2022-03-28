@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites.fields

import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.businesses.params.InviteToShareReportsParams as Params

data class InviteToShareFormFields(
    val to: EmailInputField = EmailInputField(
        name = Params::to.name,
        label = "To",
        hint = "john@doe.com"
    ),
    val subject: TextInputField = TextInputField(
        name = Params::subject.name,
        label = "Subject",
        hint = "Enter invite subject here",
        value = Params.DEFAULT_INVITE_SUBJECT
    ),
    val message: TextInputField = TextInputField(
        name = Params::message.name,
        label = "Message",
        hint = "Enter invite message here",
        value = Params.DEFAULT_INVITE_MESSAGE
    ),
    val sendInvite: ButtonInputField = ButtonInputField(
        name = "send",
        text = "Send Invite"
    )
) {
    @JsName("_ignore_copyContactEmailAndMessage")
    fun copy(contactEmail: String, message: String) = copy(
        to = to.copy(value = contactEmail),
        message = this.message.copy(value = message)
    )
}