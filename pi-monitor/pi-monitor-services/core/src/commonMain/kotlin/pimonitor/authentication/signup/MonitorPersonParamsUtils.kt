package pimonitor.authentication.signup

import contacts.Email
import pimonitor.Monitor

fun IndividualRegistrationParams.toPerson() = Monitor.Person(
    name = name,
    email = Email(email)
)