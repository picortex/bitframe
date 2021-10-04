package core.authentication

import bitframe.authentication.signin.SignInCredentials
import pimonitor.authentication.signup.IndividualRegistrationParams

internal fun IndividualRegistrationParams.credentials() = SignInCredentials(
    alias = email,
    password = password
)