package bitframe.authentication.signin

sealed class SignInIntent {
    data class Submit(val credentials: SignInCredentials) : SignInIntent()
}