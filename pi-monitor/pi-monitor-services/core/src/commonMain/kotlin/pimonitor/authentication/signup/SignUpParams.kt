package pimonitor.authentication.signup

import kotlinx.serialization.Serializable

@Serializable
sealed class SignUpParams {
    @Serializable
    data class Individual(
        val name: String,
        val email: String,
        val password: String
    ) : SignUpParams()

    @Serializable
    sealed class Cooperate(
        val businessName: String,
        val personName: String,
        val personEmail: String,
        val password: String
    ) : SignUpParams()
}