package pimonitor

import kotlinx.serialization.Serializable

@Serializable
data class MonitorPersonParams(
    val name: String?,
    val email: String?,
    val password: String?
)