@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.params

import bitframe.core.UserEmail
import identifier.Email
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InviteToShareReportsRawParams : InviteToShareReportsRawFormParams {
    val businessId: String
}

fun InviteToShareReportsParams(monitored: MonitoredBusinessSummary) = InviteToShareReportsParams(
    businessId = monitored.uid,
    to = monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${monitored.name}"),
    subject = InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT,
    message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
)

fun InviteToShareReportsRawParams.toValidatedInviteToShareReportParams() = InviteToShareReportsParams(
    businessId = businessId,
    to = requiredNotBlank(::to).let { Email(it).value },
    subject = requiredNotBlank(::subject),
    message = requiredNotBlank(::message)
)