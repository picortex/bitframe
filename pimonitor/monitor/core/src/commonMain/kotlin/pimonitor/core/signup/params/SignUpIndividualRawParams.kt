@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import bitframe.core.signin.SignInParams
import bitframe.core.users.RegisterUserParams
import identifier.Name
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface SignUpIndividualRawParams : SignUpRawParams {
    val name: String
    val email: String
}

fun SignUpIndividualRawParams.toSignInParams() = SignInParams(
    identifier = requiredNotBlank(::email),
    password = requiredNotBlank(::password)
)

fun SignUpIndividualRawParams.toValidatedSignUpParams() = SignUpIndividualParams(
    name = requiredNotBlank(::name),
    email = requiredNotBlank(::email),
    password = requiredNotBlank(::password),
)

fun SignUpIndividualRawParams.toRegisterUserParams() = RegisterUserParams(
    userName = requiredNotBlank(::name),
    userIdentifier = requiredNotBlank(::email),
    userPassword = requiredNotBlank(::password),
    userType = USER_TYPE.MONITOR,
    spaceName = "${Name(name).first}'s Space",
    spaceType = SPACE_TYPE.INDIVIDUAL_MONITOR,
    spaceScope = SPACE_SCOPE
)