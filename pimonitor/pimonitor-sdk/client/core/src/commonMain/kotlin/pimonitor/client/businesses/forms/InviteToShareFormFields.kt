@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses.forms

import bitframe.core.UserEmail
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName

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
        value = InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT
    ),
    val message: TextInputField = TextInputField(
        name = "message",
        label = "Message",
        hint = "Enter invite message here",
        value = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
    ),
    val sendInvite: ButtonInputField = ButtonInputField(
        name = "send",
        text = "Send Invite"
    )
) {
    @JsName("_ignore_copyParams")
    fun copy(params: CreateMonitoredBusinessRawParams) = copy(
        to = to.copy(value = params.contactEmail),
    )

    @JsName("_ignore_copyMonitored")
    fun copy(monitored: MonitoredBusinessSummary) = copy(
        to = to.copy(value = monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${monitored.name}")),
    )

    @JsName("_ignore_copyContactEmail")
    fun copy(contactEmail: String) = copy(
        to = to.copy(value = contactEmail),
    )
}