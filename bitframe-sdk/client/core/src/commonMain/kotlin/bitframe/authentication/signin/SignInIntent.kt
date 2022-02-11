package bitframe.authentication.signin

import bitframe.actors.spaces.Space

sealed class SignInIntent {
    object InitForm : SignInIntent()
    data class Submit(val credentials: IRawSignInCredentials) : SignInIntent()
    data class ResolveConundrum(val space: Space) : SignInIntent()
}