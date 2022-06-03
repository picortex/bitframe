package pimonitor.core.contacts

import kotlinx.serialization.Serializable

@Serializable
data class ContactsFilter(
    val uid: String
)