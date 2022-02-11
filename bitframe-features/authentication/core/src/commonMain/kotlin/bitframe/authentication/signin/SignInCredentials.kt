@file:JsExport

package bitframe.authentication.signin

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * a model for holding credentials for a basic authentication
 * @param identifier maybe an email or a phone
 * @param password the secret phrase to grant the user access
 */
@Serializable
data class SignInCredentials(
    /**
     * Can be an email, phone
     */
    val identifier: String,
    val password: String
)