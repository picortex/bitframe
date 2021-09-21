package integration.ktor.authentication

import bitframe.authentication.LoginCredentials
import pimonitor.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = LoginCredentials(
    alias = email.toString(),
    password = password.toString()
)