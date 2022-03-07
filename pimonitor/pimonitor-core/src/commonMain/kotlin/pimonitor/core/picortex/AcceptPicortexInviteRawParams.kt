package pimonitor.core.picortex

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface AcceptPicortexInviteRawParams {
    val inviteId: String
}

fun AcceptPicortexInviteRawParams.toValidatedInviteParams() = AcceptPicortexInviteParams(
    inviteId = requiredNotBlank(::inviteId)
)