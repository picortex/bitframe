@file:JsExport

package pimonitor.evaluation.businesses

import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef
import pimonitor.monitors.MonitorsService
import kotlin.js.JsExport

abstract class BusinessesService(
    open val config: BusinessesServiceConfig
) {
    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusiness) = Event(CREATE_BUSINESS_EVENT_ID, business)
    }

    protected val scope get() = config.scope
    protected val bus get() = config.bus

    abstract fun all(): Later<List<MonitoredBusiness>>

    protected abstract fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ): Later<MonitoredBusiness>

    fun create(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ) = scope.later {
        val business = executeCreate(params, monitorRef).await()
        bus.dispatch(createBusinessEvent(business))
        business
    }
}