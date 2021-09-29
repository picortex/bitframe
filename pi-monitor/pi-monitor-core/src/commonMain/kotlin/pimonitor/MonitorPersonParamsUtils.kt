package pimonitor

import contacts.Email

fun IndividualRegistrationParams.toPerson() = Monitor.Person(
    name = name ?: throw IllegalArgumentException("Name must not be null"),
    email = Email(email ?: throw IllegalArgumentException("Email must not be null"))
)