package pimonitor

import contacts.Email
import pimonitor.authentication.signup.IndividualRegistrationParams

fun IndividualRegistrationParams.toPerson() = Monitor.Person(
    name = name,
    email = Email(email)
)