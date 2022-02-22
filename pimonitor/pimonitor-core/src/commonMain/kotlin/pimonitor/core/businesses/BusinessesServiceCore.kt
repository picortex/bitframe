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
    companion object {
        const val CREATE_BUSINESS_EVENT_TOPIC = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusinessSummary) = Event(business, CREATE_BUSINESS_EVENT_TOPIC)
    }

    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>): Later<CreateMonitoredBusinessParams>

    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<BusinessFilter>): Later<List<MonitoredBusinessSummary>>
}