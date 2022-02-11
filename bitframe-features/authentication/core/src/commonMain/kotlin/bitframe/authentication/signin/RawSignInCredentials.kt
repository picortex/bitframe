package bitframe.authentication.signin

import identifier.Email
import identifier.Phone
import kotlinx.serialization.Serializable

@Serializable
data class RawSignInCredentials(
    override val identifier: String,
    override val password: String,
) : IRawSignInCredentials {
    override val email: String?
        get() = try {
            Email(identifier).value
        } catch (err: Throwable) {
            null
        }

    override val phone: String?
        get() = try {
            Phone(identifier).value
        } catch (err: Throwable) {
            null
        }
}