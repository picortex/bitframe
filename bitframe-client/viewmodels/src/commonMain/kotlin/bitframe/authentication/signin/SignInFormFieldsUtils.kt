package bitframe.authentication.signin

internal fun SignInFormFields.copy(i: SignInIntent.Submit) = copy(
    email = email.copy(value = i.credentials.alias),
    password = password.copy(value = i.credentials.password)
)