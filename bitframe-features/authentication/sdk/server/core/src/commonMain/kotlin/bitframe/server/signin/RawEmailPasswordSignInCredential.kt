package bitframe.server.signin

import bitframe.core.signin.SignInParams
import kotlinx.serialization.Serializable

@Serializable
class RawEmailPasswordSignInCredential(
    private val email: String,
    private val password: String
) {
    fun toRawSignInCredentials() = SignInParams(email, password)
}