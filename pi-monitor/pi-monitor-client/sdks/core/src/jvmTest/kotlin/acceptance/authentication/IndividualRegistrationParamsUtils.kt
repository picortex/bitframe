package acceptance.authentication

import bitframe.authentication.signin.LoginCredentials
import pimonitor.authentication.signup.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = LoginCredentials(
    alias = email.toString(),
    password = password.toString()
)