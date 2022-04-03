package bitframe.server.signin

import bitframe.core.signin.SignInRawParams
import kotlinx.serialization.Serializable

@Serializable
class RawEmailPasswordSignInCredential(
    private val email: String,
    private val password: String
) {
    fun toRawSignInCredentials() = SignInRawParams(email, password)
}