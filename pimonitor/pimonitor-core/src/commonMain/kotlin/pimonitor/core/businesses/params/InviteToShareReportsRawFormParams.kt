@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.params

import identifier.Email
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InviteToShareReportsRawFormParams {
    val to: String
    val subject: String
    val message: String
}

fun InviteToShareReportsRawFormParams.copy(
    businessId: String
): InviteToShareReportsRawParams = InviteToShareReportsParams(
    businessId = businessId,
    to = requiredNotBlank(::to).let { Email(it).value },
    subject = requiredNotBlank(::subject),
    message = requiredNotBlank(::message)
)