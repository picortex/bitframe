package pimonitor.authentication.signup

import bitframe.authentication.users.Contacts
import bitframe.authentication.users.RegisterUserParams

fun IndividualRegistrationParams.toRegisterUserParams() = RegisterUserParams(
    name = name,
    contacts = Contacts.of(email),
    password = password
)