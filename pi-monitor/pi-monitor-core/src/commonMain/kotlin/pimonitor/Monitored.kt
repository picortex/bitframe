package pimonitor

import contacts.Email
import kotlinx.serialization.Serializable

@Serializable
data class Monitored(
    val uid: String,
    val name: String,
    val email: Email,
    val logo: String? = null
)
