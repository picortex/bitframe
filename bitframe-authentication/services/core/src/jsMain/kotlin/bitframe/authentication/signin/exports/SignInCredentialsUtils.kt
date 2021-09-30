package bitframe.authentication.signin.exports

import bitframe.authentication.signin.SignInCredentials as SafeCredentials

fun SignInCredentials.toSignInCredentials() = SafeCredentials(
    alias = email,
    password = password
)