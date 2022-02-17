@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import events.Event
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateBusinessParams
import kotlin.js.JsExport
import kotlin.js.JsName

interface BusinessesService {
    val config: ServiceConfig

    companion object {
        const val CREATE_BUSINESS_EVENT_TOPIC = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusinessSummary) = Event(business, CREATE_BUSINESS_EVENT_TOPIC)
    }

    val scope get() = config.scope
    val bus get() = config.bus

    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<CreateBusinessParams>): Later<CreateBusinessParams>

    fun all(rb: RequestBody.Authorized<String>): Later<List<MonitoredBusinessSummary>>
}