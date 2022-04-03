package bitframe.server.signin

import bitframe.core.signin.SignInRawParams
import kotlinx.serialization.Serializable

@Serializable
class RawPhonePasswordSignInCredential(
    private val phone: String,
    private val password: String
) {
    fun toRawSignInCredentials() = SignInRawParams(phone, password)
}