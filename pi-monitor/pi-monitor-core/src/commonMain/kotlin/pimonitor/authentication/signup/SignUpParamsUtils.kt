package pimonitor.authentication.signup

import bitframe.core.users.RegisterUserParams
import bitframe.authentication.signin.SignInCredentials
import identifier.Name
import pimonitor.spaces.SPACE_TYPE
import pimonitor.users.USER_TYPE

fun SignUpParams.Individual.toRegisterUserParams() = RegisterUserParams(
    userName = name,
    userIdentifier = email,
    userPassword = password,
    userType = USER_TYPE.MONITOR,
    spaceName = "${Name(name).first}'s Space",
    spaceType = SPACE_TYPE.INDIVIDUAL_MONITOR
)

fun SignUpParams.Business.toRegisterUserParams() = RegisterUserParams(
    userName = individualName,
    userIdentifier = individualEmail,
    userPassword = password,
    userType = USER_TYPE.MONITOR,
    spaceName = businessName,
    spaceType = SPACE_TYPE.COOPERATE_MONITOR,
)

fun SignUpParams.toRegisterUserParams(): RegisterUserParams = when (this) {
    is SignUpParams.Individual -> toRegisterUserParams()
    is SignUpParams.Business -> toRegisterUserParams()
}

fun SignUpParams.toCredentials() = when (this) {
    is SignUpParams.Individual -> SignInCredentials(email, password)
    is SignUpParams.Business -> SignInCredentials(individualEmail, password)
}