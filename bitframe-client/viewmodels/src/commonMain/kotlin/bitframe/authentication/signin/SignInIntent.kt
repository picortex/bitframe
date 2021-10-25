package bitframe.authentication.signin

import bitframe.authentication.spaces.Space

sealed class SignInIntent {
    data class Submit(val credentials: SignInCredentials) : SignInIntent()
    data class Resolve(val space: Space) : SignInIntent()
}