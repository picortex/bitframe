package pimonitor.core.signup.params

import kotlinx.serialization.Serializable

@Serializable
data class SignUpIndividualParams(
    override val name: String,
    override val email: String,
    override val password: String
) : SignUpIndividualRawParams