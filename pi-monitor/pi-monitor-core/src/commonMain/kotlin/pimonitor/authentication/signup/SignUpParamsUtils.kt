package pimonitor.authentication.signup

import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.UserRef
import bitframe.authentication.signin.SignInCredentials
import identifier.Email
import identifier.Name
import pimonitor.businesses.Business
import pimonitor.spaces.PiMonitorSpaceType
import pimonitor.users.PiMonitorUserType

fun SignUpParams.Individual.toRegisterUserParams() = RegisterUserParams(
    userName = name,
    userIdentifier = email,
    userPassword = password,
    userType = PiMonitorUserType.Monitor,
    spaceName = "${Name(name).first}'s Space",
    spaceType = PiMonitorSpaceType.IndividualMonitor
)

fun SignUpParams.Business.toRegisterUserParams() = RegisterUserParams(
    userName = individualName,
    userIdentifier = individualEmail,
    userPassword = password,
    userType = PiMonitorUserType.Monitor,
    spaceName = businessName,
    spaceType = PiMonitorSpaceType.CooperateMonitor,
)

fun SignUpParams.toRegisterUserParams() = when (this) {
    is SignUpParams.Individual -> toRegisterUserParams()
    is SignUpParams.Business -> toRegisterUserParams()
}

fun SignUpParams.toCredentials() = when (this) {
    is SignUpParams.Individual -> SignInCredentials(email, password)
    is SignUpParams.Business -> SignInCredentials(individualEmail, password)
}