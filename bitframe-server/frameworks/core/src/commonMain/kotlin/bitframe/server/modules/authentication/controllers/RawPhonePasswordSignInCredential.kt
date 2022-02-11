package bitframe.server.modules.authentication.controllers

import bitframe.authentication.signin.RawSignInCredentials
import kotlinx.serialization.Serializable

@Serializable
class RawPhonePasswordSignInCredential(
    val phone: String,
    val password: String
) {
    fun toRawSignInCredentials() = RawSignInCredentials(phone, password)
}