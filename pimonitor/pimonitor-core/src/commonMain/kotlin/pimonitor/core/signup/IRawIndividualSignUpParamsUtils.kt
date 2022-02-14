package pimonitor.core.signup

import bitframe.core.signin.SignInCredentials
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