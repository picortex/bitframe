package bitframe.server.signin

import bitframe.core.signin.SignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawEmailPasswordSignInCredential(
    private val email: String,
    private val password: String
) {
    fun toRawSignInCredentials() = SignInCredentials(email, password)
}