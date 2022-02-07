package bitframe.authentication.signin

internal fun SignInFormFields.copy(i: SignInIntent.Submit) = copy(
    credentials = i.credentials
)

internal fun SignInFormFields.copy(credentials: SignInCredentials) = copy(
    email = email.copy(value = credentials.identifier),
    password = password.copy(value = credentials.password)
)