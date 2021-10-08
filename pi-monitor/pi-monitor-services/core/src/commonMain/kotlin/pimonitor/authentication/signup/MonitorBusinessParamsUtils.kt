package pimonitor.authentication.signup

import contacts.Email
import pimonitor.Monitor

fun MonitorBusinessParams.toBusiness() = Monitor.Business(
    name = name ?: throw IllegalArgumentException("Name must not be null"),
    email = Email(email ?: throw IllegalArgumentException("Email must not be null"))
)