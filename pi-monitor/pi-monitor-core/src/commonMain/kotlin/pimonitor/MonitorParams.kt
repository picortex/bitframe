package pimonitor

import kotlinx.serialization.Serializable

@Serializable
data class MonitorParams(
    val name: String?,
    val email: String?,
    val password: String? = null,
    val confirmPassword: String? = null
)