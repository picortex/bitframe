package pimonitor.authentication.signup

import bitframe.authentication.users.Contacts

fun IndividualRegistrationParams.toRegisterUserParams() = RegisterUserParams(
    name = name ?: throw RuntimeException("Name must not be null"),
    contacts = Contacts.of(email ?: throw RuntimeException("Name must not be null")),
    password = password ?: throw RuntimeException("Password must not be null")
)