package pimonitor.authentication.signup

import kotlinx.serialization.Serializable

@Serializable
data class MonitorBusinessParams(
    val name: String?,
    val email: String?
)