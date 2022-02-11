package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.SignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawPhonePasswordSignInCredential(
    val phone: String,
    val password: String
) {
    fun toRawSignInCredentials() = SignInCredentials(phone, password)
}