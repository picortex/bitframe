@file:JsExport

package pimonitor.monitored

import bitframe.actors.modal.HasId
import identifier.Email
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.monitors.MonitorRef
import kotlin.js.JsExport

@Serializable
data class MonitoredBusiness(
    val name: String,
    val contacts: List<ContactPerson>,
    val monitorRef: MonitorRef,
    override val uid: String = HasId.UNSET,
) : HasId {
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

    override fun copy(id: String) = copy(uid = id)
}