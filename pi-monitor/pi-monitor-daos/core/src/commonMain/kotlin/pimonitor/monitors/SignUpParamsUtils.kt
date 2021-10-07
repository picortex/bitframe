package pimonitor.monitors

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

fun SignUpParams.Business.toRegisterSpaceParams() = RegisterSpaceParams(
    name = businessName
)