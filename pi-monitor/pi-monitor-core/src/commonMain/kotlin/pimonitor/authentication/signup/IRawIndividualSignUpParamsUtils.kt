package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInCredentials
import validation.required

fun IRawIndividualSignUpParams.toSignUpParams() = SignUpParams.Individual(
    name = required(::name),
    email = required(::email),
    password = required(::password)
)

fun IRawIndividualSignUpParams.toRawCredentials() = SignInCredentials(
    identifier = required(::email),
    password = required(::password)
)