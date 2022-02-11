package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.RawSignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawEmailPasswordSignInCredential(
    private val email: String,
    private val password: String
) {
    fun toRawSignInCredentials() = RawSignInCredentials(email, password)
}