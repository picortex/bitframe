@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInRawParams
import bitframe.core.users.RegisterUserParams
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface SignUpBusinessRawParams : SignUpRawParams {
    val businessName: String
    val individualName: String
    val individualEmail: String
}

fun SignUpBusinessRawParams.toSignInParams() = SignInParams(
    identifier = requiredNotBlank(::individualEmail),
    password = requiredNotBlank(::password)
)

fun SignUpBusinessRawParams.toValidatedSignUpParams() = SignUpBusinessParams(
    businessName = requiredNotBlank(::businessName),
    individualName = requiredNotBlank(::individualName),
    individualEmail = requiredNotBlank(::individualEmail),
    password = requiredNotBlank(::password),
)

fun SignUpBusinessRawParams.toRegisterUserParams() = RegisterUserParams(
    userName = requiredNotBlank(::individualName),
    userIdentifier = requiredNotBlank(::individualEmail),
    userPassword = requiredNotBlank(::password),
    userType = USER_TYPE.MONITOR,
    spaceName = requiredNotBlank(::businessName),
    spaceType = SPACE_TYPE.COOPERATE_MONITOR,
    spaceScope = SPACE_SCOPE
)