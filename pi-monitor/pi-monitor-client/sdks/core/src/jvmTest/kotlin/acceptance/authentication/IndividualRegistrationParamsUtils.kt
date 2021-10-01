package acceptance.authentication

import bitframe.authentication.signin.SignInCredentials
import pimonitor.authentication.signup.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = SignInCredentials(
    alias = email.toString(),
    password = password.toString()
)