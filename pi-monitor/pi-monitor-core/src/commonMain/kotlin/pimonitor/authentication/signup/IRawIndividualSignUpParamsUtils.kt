package pimonitor.authentication.signup

import bitframe.authentication.signin.RawSignInCredentials
import validation.required

fun IRawIndividualSignUpParams.toSignUpParams() = SignUpParams.Individual(
    name = required(::name),
    email = required(::email),
    password = required(::password)
)

fun IRawIndividualSignUpParams.toRawCredentials() = RawSignInCredentials(
    identifier = required(::email),
    password = required(::password)
)