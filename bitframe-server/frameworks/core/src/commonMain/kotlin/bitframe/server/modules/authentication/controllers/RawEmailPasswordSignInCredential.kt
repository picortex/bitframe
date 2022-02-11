package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.SignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawEmailPasswordSignInCredential(
    private val email: String,
    private val password: String
) {
    fun toRawSignInCredentials() = SignInCredentials(email, password)
}