package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.RegisterUserParams
import bitframe.authentication.users.UserRef
import identifier.Email
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor

fun SignUpParams.Individual.toRegisterUserParams() = RegisterUserParams(
    name = name, contacts = Contacts.Email(email), password = password
)

fun SignUpParams.Business.toRegisterUserParams() = RegisterUserParams(
    name = individualName,
    contacts = Contacts.Email(individualEmail),
    password = password
)

fun SignUpParams.Business.toCreateSpaceParams() = CreateSpaceParams(
    name = businessName
)

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