@file:JsExport

package pimonitor.client.evaluation.businesses

import events.Event
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

abstract class BusinessesService(
    override val config: BusinessesServiceConfig
) : BusinessesService(config) {

    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
//        fun createBusinessEvent(business: MonitoredBusiness) = Event(business,CREATE_BUSINESS_EVENT_ID )
    }

    fun create(
        params: CreateMonitoredBusinessParams
    ) = create(params, config.monitorSession.value.currentMonitorOrThrow().ref())
}