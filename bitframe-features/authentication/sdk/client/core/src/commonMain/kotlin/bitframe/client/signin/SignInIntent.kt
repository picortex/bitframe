package bitframe.client.signin

import bitframe.core.Space
import bitframe.core.signin.SignInRawParams

sealed class SignInIntent {
    object InitForm : SignInIntent()
    data class Submit(val params: SignInRawParams) : SignInIntent()
    data class ResolveConundrum(val space: Space) : SignInIntent()
}