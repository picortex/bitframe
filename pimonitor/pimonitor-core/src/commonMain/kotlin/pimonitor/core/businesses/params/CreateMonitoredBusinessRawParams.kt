package pimonitor.core.businesses.params

import bitframe.core.users.RegisterUserParams
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import validation.required
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateMonitoredBusinessRawParams {
    val businessName: String
    val contactName: String
    val contactEmail: String
    val sendInvite: Boolean
}

fun CreateMonitoredBusinessRawParams.toValidatedCreateBusinessParams() = CreateMonitoredBusinessParams(
    businessName = requiredNotBlank(::businessName),
    contactName = requiredNotBlank(::contactName),
    contactEmail = requiredNotBlank(::contactEmail),
    sendInvite = required(::sendInvite)
)

fun CreateMonitoredBusinessRawParams.toRegisterUserParams() = RegisterUserParams(
    userName = contactName,
    userIdentifier = contactEmail,
    userType = USER_TYPE.CONTACT,
    userPassword = contactEmail,
    spaceName = businessName,
    spaceType = SPACE_TYPE.MONITORED,
    spaceScope = SPACE_SCOPE
)