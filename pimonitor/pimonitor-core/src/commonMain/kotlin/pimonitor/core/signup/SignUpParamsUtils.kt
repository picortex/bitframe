package pimonitor.core.signup

import bitframe.core.signin.SignInCredentials
import bitframe.core.users.RegisterUserParams
import identifier.Name
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE

fun SignUpParams.Individual.toRegisterUserParams() = RegisterUserParams(
    userName = name,
    userIdentifier = email,
    userPassword = password,
    userType = USER_TYPE.MONITOR,
    spaceName = "${Name(name).first}'s Space",
    spaceType = SPACE_TYPE.INDIVIDUAL_MONITOR,
    spaceScope = SPACE_SCOPE
)

fun SignUpParams.Business.toRegisterUserParams() = RegisterUserParams(
    userName = individualName,
    userIdentifier = individualEmail,
    userPassword = password,
    userType = USER_TYPE.MONITOR,
    spaceName = businessName,
    spaceType = SPACE_TYPE.COOPERATE_MONITOR,
    spaceScope = SPACE_SCOPE
)

fun SignUpParams.toRegisterUserParams(): RegisterUserParams = when (this) {
    is SignUpParams.Individual -> toRegisterUserParams()
    is SignUpParams.Business -> toRegisterUserParams()
}

fun SignUpParams.toCredentials() = when (this) {
    is SignUpParams.Individual -> SignInCredentials(email, password)
    is SignUpParams.Business -> SignInCredentials(individualEmail, password)
}