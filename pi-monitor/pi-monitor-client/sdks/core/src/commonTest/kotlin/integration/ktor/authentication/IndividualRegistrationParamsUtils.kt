package integration.ktor.authentication

import pimonitor.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = LoginCredentials(
    alias = email.toString(),
    password = password.toString()
)