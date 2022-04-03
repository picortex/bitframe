package bitframe.server.signin

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.map
import bitframe.core.signin.SignInRawParams
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString

class SignInParser(private val config: ServiceConfigDaod) : StringFormat by config.json {
    fun parseCredentialsFromString(body: String) = try {
        decodeFromString<RequestBody.UnAuthorized<SignInRawParams>>(body)
    } catch (err: Throwable) {
        null
    } ?: try {
        decodeFromString<RequestBody.UnAuthorized<RawEmailPasswordSignInCredential>>(body).map { it.toRawSignInCredentials() }
    } catch (err: Throwable) {
        null
    } ?: try {
        decodeFromString<RequestBody.UnAuthorized<RawPhonePasswordSignInCredential>>(body).map { it.toRawSignInCredentials() }
    } catch (err: Throwable) {
        null
    } ?: error("Failed to decode credentials in body")
}