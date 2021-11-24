@file:JsExport

package bitframe.client.evaluation.businesses

import bitframe.events.Event
import bitframe.evaluation.businesses.BusinessesService
import bitframe.monitored.CreateMonitoredBusinessParams
import bitframe.monitored.MonitoredBusiness
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
    ) = create(params, config.monitorSession.value.currentMonitorOrThrow().ref())
}