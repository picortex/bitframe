package pimonitor.core.sage

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface AcceptSageOneInviteRawParams {
    val inviteId: String
    val companyId: String
    val username: String
    val password: String
}

fun AcceptSageOneInviteRawParams.toValidatedInviteParams() = AcceptSageOneInviteParams(
    inviteId = requiredNotBlank(::inviteId),
    companyId = requiredNotBlank(::companyId),
    username = requiredNotBlank(::username),
    password = requiredNotBlank(::password),
)