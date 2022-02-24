@file:JsExport

package pimonitor.core.businesses

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitoredBusinessRef(
    val uid: String,
    val spaceId: String,
    val owningSpaceId: String,
    val name: String,
    val address: String
)