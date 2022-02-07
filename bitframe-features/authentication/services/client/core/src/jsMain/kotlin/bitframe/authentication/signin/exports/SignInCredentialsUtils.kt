package bitframe.authentication.signin.exports

import bitframe.authentication.signin.SignInCredentials as SafeCredentials

fun SignInCredentials.toSignInCredentials() = SafeCredentials(
    identifier = email,
    password = password
)