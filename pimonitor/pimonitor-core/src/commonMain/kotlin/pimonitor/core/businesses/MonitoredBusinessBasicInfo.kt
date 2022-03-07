@file:JsExport

package pimonitor.core.businesses

import bitframe.core.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitoredBusinessBasicInfo(
    override val name: String,
    override val owningSpaceId: String, // space of the monitor monitoring this business
    val dashboard: DashboardOperational = DASHBOARD_OPERATIONAL.NONE,
    override val email: String = "",
    override val address: String = "",
    val industry: String = "",
    override val logoUrl: String? = null,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Business() {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}