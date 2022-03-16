package pimonitor.core.sage

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface AcceptSageOneInviteRawParams : AcceptSageOneInviteRawFormParams {
    val inviteId: String
}

fun AcceptSageOneInviteRawParams.toValidatedInviteParams() = AcceptSageOneInviteParams(
    inviteId = requiredNotBlank(::inviteId),
    companyId = requiredNotBlank(::companyId),
    username = requiredNotBlank(::username),
    password = requiredNotBlank(::password),
)