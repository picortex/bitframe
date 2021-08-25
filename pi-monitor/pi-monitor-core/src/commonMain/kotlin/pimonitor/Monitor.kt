package pimonitor

import contacts.Email
import kotlinx.serialization.Serializable

@Serializable
data class Monitor(
    val uid: String,
    val business: Business,
    val contacts: List<Person>
) {
    init {
        require(contacts.isNotEmpty()) { "A Monitor must have at least one contact person" }
    }

    @Serializable
    data class Business(
        val name: String,
        val email: Email,
        val logo: String?
    )

    @Serializable
    data class Person(
        val name: String,
        val email: Email,
    )

    val primaryContact get() = contacts.first()
}