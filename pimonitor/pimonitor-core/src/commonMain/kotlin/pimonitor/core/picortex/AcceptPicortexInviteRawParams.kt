package pimonitor.core.picortex

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface AcceptPicortexInviteRawParams {
    val inviteId: String
    val subdomain: String
    val secret: String
}

fun AcceptPicortexInviteRawParams.toValidatedInviteParams() = AcceptPicortexInviteParams(
    inviteId = requiredNotBlank(::inviteId),
    subdomain = requiredNotBlank(::subdomain),
    secret = requiredNotBlank(::secret)
)