package bitframe.client.signin

import bitframe.core.signin.RawSignInCredentials

internal fun SignInFormFields.copy(i: SignInIntent.Submit) = copy(credentials = i.credentials)

internal fun SignInFormFields.copy(credentials: RawSignInCredentials) = copy(
    email = email.copy(value = credentials.email),
    password = password.copy(value = credentials.password)
)