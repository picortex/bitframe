@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import events.Event
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessesServiceCore {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>): Later<CreateMonitoredBusinessParams>

    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<BusinessFilter>): Later<List<MonitoredBusinessSummary>>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<String>>
}