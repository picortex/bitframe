package pimonitor

import contacts.Email

fun MonitorBusinessParams.toBusiness() = Monitor.Business(
    name = name ?: throw IllegalArgumentException("Name must not be null"),
    email = Email(email ?: throw IllegalArgumentException("Email must not be null"))
)