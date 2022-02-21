package bitframe.server.controllers

import bitframe.core.signin.SignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawPhonePasswordSignInCredential(
    val phone: String,
    val password: String
) {
    fun toRawSignInCredentials() = SignInCredentials(phone, password)
}