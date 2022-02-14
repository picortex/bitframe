package bitframe.client.signin

import bitframe.core.Space
import bitframe.core.signin.RawSignInCredentials

sealed class SignInIntent {
    object InitForm : SignInIntent()
    data class Submit(val credentials: RawSignInCredentials) : SignInIntent()
    data class ResolveConundrum(val space: Space) : SignInIntent()
}