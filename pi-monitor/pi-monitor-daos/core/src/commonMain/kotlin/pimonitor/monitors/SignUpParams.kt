package pimonitor.monitors

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
    data class Business(
        val businessName: String,
        val individualName: String,
        val individualEmail: String,
        val password: String
    ) : SignUpParams()
}