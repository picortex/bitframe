package pimonitor.authentication.signup

import kotlinx.serialization.Serializable

/**
 * Params for registering an individual
 *
 * Do not be fooled by the nullability of the parameters
 * they are nullable for interoperability reasons with js.
 */
@Serializable
data class IndividualRegistrationParams(
    val name: String,
    val email: String,
    val password: String
)