@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.params

import bitframe.core.UserEmail
import identifier.Email
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InviteToShareReportsRawParams {
    val business: MonitoredBusinessSummary
    val to: String
    val subject: String
    val message: String
}

fun InviteToShareReportsParams(monitored: MonitoredBusinessSummary) = InviteToShareReportsParams(
    business = monitored,
    to = monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${monitored.name}"),
    subject = InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT,
    message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
)

fun InviteToShareReportsRawParams.toValidatedInviteToShareReportParams() = InviteToShareReportsParams(
    business = business,
    to = requiredNotBlank(::to).let { Email(it).value },
    subject = requiredNotBlank(::subject),
    message = requiredNotBlank(::message)
)