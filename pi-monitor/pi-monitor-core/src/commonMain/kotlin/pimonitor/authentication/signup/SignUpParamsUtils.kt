package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.RegisterUserParams

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