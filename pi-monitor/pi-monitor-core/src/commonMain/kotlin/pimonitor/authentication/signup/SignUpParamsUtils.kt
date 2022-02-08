package pimonitor.authentication.signup

import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.UserRef
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.spaces.CreateSpaceParams
import identifier.Email
import identifier.Name
import pimonitor.authentication.SpaceTypes
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor

fun SignUpParams.Individual.toRegisterUserParams() = RegisterUserParams(
    userName = name,
    userIdentifier = email,
    userPassword = password,
    spaceName = "${Name(name).first}'s Space",
    spaceType = SpaceTypes.MONITOR_INDIVIDUAL.name,
    spaceScope = SpaceTypes.MONITOR_INDIVIDUAL.name
)

fun SignUpParams.Business.toRegisterUserParams() = RegisterUserParams(
    userName = individualName,
    userIdentifier = individualEmail,
    userPassword = password,
    spaceName = businessName,
    spaceType = SpaceTypes.MONITOR_BUSINESS.name,
    spaceScope = SpaceTypes.MONITOR_BUSINESS.name,
)

fun SignUpParams.toRegisterUserParams() = when (this) {
    is SignUpParams.Individual -> toRegisterUserParams()
    is SignUpParams.Business -> toRegisterUserParams()
}

fun SignUpParams.toCredentials() = when (this) {
    is SignUpParams.Individual -> SignInCredentials(email, password)
    is SignUpParams.Business -> SignInCredentials(individualEmail, password)
}

fun SignUpParams.Individual.toMonitor(uid: String, ref: UserRef) = IndividualMonitor(uid, name, Email(email), ref)

fun SignUpParams.Business.toMonitor(uid: String, ref: UserRef) = CooperateMonitor(
    uid,
    name = businessName,
    email = Email(individualEmail),
    contacts = listOf(
        CooperateMonitor.ContactPerson(
            uid,
            name = individualName,
            email = Email(individualEmail),
            ref
        )
    )
)

fun SignUpParams.toMonitor(uid: String, ref: UserRef) = when (this) {
    is SignUpParams.Individual -> toMonitor(uid, ref)
    is SignUpParams.Business -> toMonitor(uid, ref)
}