package pimonitor

import contacts.Email

fun MonitorParams.toBusiness() = Monitor.Business(
    name = name ?: error("Business name must not be null"),
    email = Email(email ?: "Email must not be null")
)

fun MonitorParams.toPerson() = Monitor.Person(
    name = name ?: error("Person name must not be null"),
    email = Email(email ?: error("Person email must not be null"))
)