package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.RegisterUserParams

fun IndividualRegistrationParams.toRegisterUserParamsNew() = RegisterUserParams(
    name = name,
    contacts = Contacts.of(email),
    password = password
)

fun IndividualRegistrationParams.credentials() = SignInCredentials(
    alias = email,
    password = password
)