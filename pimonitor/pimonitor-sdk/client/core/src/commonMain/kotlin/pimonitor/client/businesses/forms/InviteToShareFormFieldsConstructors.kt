package pimonitor.client.businesses.forms

import bitframe.core.UserEmail
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.invites.Invite
import presenters.fields.ButtonInputField
import presenters.fields.EmailInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName

fun InviteToShareFormFields(contactEmail: String): InviteToShareFormFields {
    val defaultFields = InviteToShareFormFields()
    return defaultFields.copy(
        to = defaultFields.to.copy(value = contactEmail),
    )
}