@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites.fields

import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.businesses.params.InviteToShareReportsParams as Params

class InviteToShareFields(contactEmail: String, message: String) {
    val to = EmailInputField(
        name = Params::to.name,
        label = "To",
        hint = "john@doe.com",
        value = contactEmail
    )
    val subject = TextInputField(
        name = Params::subject.name,
        label = "Subject",
        hint = "Enter invite subject here",
        value = Params.DEFAULT_INVITE_SUBJECT
    )
    val message = TextInputField(
        name = Params::message.name,
        label = "Message",
        hint = "Enter invite message here",
        value = message
    )
}