package bitframe.authentication.signin

import bitframe.authentication.spaces.Space

sealed class SignInIntent {
    object InitForm : SignInIntent()
    data class Submit(val credentials: SignInCredentials) : SignInIntent()
    data class ResolveConundrum(val space: Space) : SignInIntent()
}