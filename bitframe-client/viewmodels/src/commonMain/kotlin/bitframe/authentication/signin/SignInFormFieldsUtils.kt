package bitframe.authentication.signin

internal fun SignInFormFields.copy(i: SignInIntent.Submit) = copy(
    credentials = i.credentials
)

internal fun SignInFormFields.copy(credentials: SignInCredentials) = copy(
    email = email.copy(value = credentials.alias),
    password = password.copy(value = credentials.password)
)