package pimonitor.monitored

import kotlinx.serialization.Serializable

@Serializable
data class CreateMonitoredBusinessParams(
    val businessName: String,
    val contactName: String,
    val contactEmail: String,
    val sendInvite: Boolean = false
)