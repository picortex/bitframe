package bitframe.authentication

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * a model for holding credentials for a basic authentication
 * @param alias maybe an email, phone or username
 * @param password the secret phrase to grant the user access
 */
@Serializable
data class LoginCredentials(
    /**
     * Can be an email, phone or username
     */
    val alias: String,
    val password: String
)