@file:JsExport

package pimonitor.client.evaluation.businesses

import bitframe.events.Event
import later.await
import later.later
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef
import kotlin.js.JsExport

abstract class BusinessesService(
    override val config: BusinessesServiceConfig
) : BusinessesService(config) {

    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusiness) = Event(CREATE_BUSINESS_EVENT_ID, business)
    }

    fun create(
        params: CreateMonitoredBusinessParams
    ) = create(params, config.monitorsService.currentMonitor.ref())
}