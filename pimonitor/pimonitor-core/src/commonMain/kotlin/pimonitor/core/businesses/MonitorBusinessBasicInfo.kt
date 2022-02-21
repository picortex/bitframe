@file:JsExport

package pimonitor.core.businesses

import bitframe.core.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitorBusinessBasicInfo(
    override val spaceId: String,
    override val owningSpaceId: String,
    override val email: String = "",
    override val address: String = "",
    override val logo: String? = "",
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Business() {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}