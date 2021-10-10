@file:JsExport

package pimonitor.monitored

import contacts.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitoredBusiness(
    val uid: String,
    val name: String,
    val contacts: List<ContactPerson>
) {
    init {
        require(contacts.isNotEmpty()) { "A business must have at least one contact person" }
    }

    @Serializable
    data class ContactPerson(
        val uid: String,
        val name: String,
        val position: String,
        val email: Email
    )
}