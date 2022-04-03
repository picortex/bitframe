package pimonitor.core.signup.params

import kotlinx.serialization.Serializable

@Serializable
data class SignUpBusinessParams(
    override val businessName: String,
    override val individualName: String,
    override val individualEmail: String,
    override val password: String
) : SignUpBusinessRawParams