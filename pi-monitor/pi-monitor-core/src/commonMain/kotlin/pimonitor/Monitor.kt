package pimonitor

import bitframe.annotations.Generated
import bitframe.annotations.Module
import contacts.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * A [Monitor] is an entity that monitors at least one [Monitored] entity
 */
@JsExport
@Serializable
@Module
data class Monitor(
    @Generated val uid: String,
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