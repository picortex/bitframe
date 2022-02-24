@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.contacts

import bitframe.core.UserEmail
import bitframe.core.UserPhone
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessRef
import kotlin.js.JsExport

@Serializable
data class ContactPersonSummary(
    val userId: String,
    val name: String,
    val email: String,
    val phone: String,
    val position: String,
    val business: MonitoredBusinessRef
)