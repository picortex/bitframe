package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInCredentials
import validation.required

fun IRawBusinessSignUpParams.toSignUpParams() = SignUpParams.Business(
    businessName = required(::businessName),
    individualName = required(::individualName),
    individualEmail = required(::individualEmail),
    password = required(::password)
)

fun IRawBusinessSignUpParams.toRawCredentials() = SignInCredentials(
    identifier = required(::individualEmail),
    password = required(::password)
)