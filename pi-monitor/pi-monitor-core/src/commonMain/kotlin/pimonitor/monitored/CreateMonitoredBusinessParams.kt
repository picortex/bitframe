package pimonitor.monitored

data class CreateMonitoredBusinessParams(
    val businessName: String,
    val contactName: String,
    val contactEmail: String
)