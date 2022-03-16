package pimonitor.core.businesses.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InviteMessageRawParams {
    val businessId: String
}

fun InviteMessageRawParams.toValidatedInviteMessageParams() = InviteMessageParams(
    businessId = requiredNotBlank(::businessId)
)