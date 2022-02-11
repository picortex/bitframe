package bitframe.authentication.signin

import validation.required

fun IRawSignInCredentials.toSignInCredentials() = SignInCredentials(
    identifier = required(::email),
    password = required(::password)
)