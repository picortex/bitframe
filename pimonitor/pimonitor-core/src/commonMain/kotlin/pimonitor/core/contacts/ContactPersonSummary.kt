@file:JsExport

package pimonitor.core.contacts

import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessRef
import kotlin.js.JsExport

@Serializable
data class ContactPersonSummary(
    val userId: String,
    val name: String,
    val position: String,
    val business: MonitoredBusinessRef
)