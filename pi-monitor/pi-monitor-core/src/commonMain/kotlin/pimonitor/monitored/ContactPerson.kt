@file:JsExport

package pimonitor.monitored

import bitframe.core.actors.modal.HasId
import bitframe.core.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ContactPerson(
    val name: String,
    val userId: String,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}