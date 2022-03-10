package pimonitor.core.sage

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface AcceptSageOneInviteRawFormParams {
    val companyId: String
    val username: String
    val password: String
}

fun AcceptSageOneInviteRawFormParams.copy(
    inviteId: String
): AcceptSageOneInviteRawParams = AcceptSageOneInviteParams(
    inviteId = inviteId,
    companyId = requiredNotBlank(::companyId),
    username = requiredNotBlank(::username),
    password = requiredNotBlank(::password),
)