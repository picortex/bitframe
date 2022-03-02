package pimonitor.core.businesses.params

import identifier.Email
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InviteToShareReportsRawParams {
    val to: String
    val subject: String
    val message: String
}

fun InviteToShareReportsRawParams.toValidatedInviteToShareReportParams() = InviteToShareReportsParams(
    to = requiredNotBlank(::to).let { Email(it).value },
    subject = requiredNotBlank(::subject),
    message = requiredNotBlank(::message)
)