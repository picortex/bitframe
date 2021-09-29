package acceptance.authentication

import bitframe.authentication.signin.LoginCredentials
import pimonitor.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = LoginCredentials(
    alias = email.toString(),
    password = password.toString()
)