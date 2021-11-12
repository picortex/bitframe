@file:JsExport

package pimonitor.monitored

import contacts.Email
import kotlinx.serialization.Serializable
import pimonitor.monitors.MonitorRef
import kotlin.js.JsExport

@Serializable
data class MonitoredBusiness(
    val uid: String,
    val name: String,
    val contacts: List<ContactPerson>,
    val monitorRef: MonitorRef
) {
    init {
        require(contacts.isNotEmpty()) { "A business must have at least one contact person" }
    }

    val contactsArray get() = contacts.toTypedArray()

    @Serializable
    data class ContactPerson(
        val uid: String,
        val name: String,
        val position: String,
        val email: Email
    )
}