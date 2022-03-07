@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.contacts.ContactPersonBusinessInfo
import kotlin.js.JsExport

@Serializable
class CreateMonitoredBusinessResult(
    val params: CreateMonitoredBusinessParams,
    val business: MonitoredBusinessBasicInfo,
    val contact: ContactPersonBusinessInfo
)