@file:JsExport

package pimonitor.core.monitored

import bitframe.core.Savable
import bitframe.core.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitoredBusiness(
    val name: String,
    val spaceId: String,
    val email: String = "",
    val address: String = "",
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}