@file:JsExport

package pimonitor.evaluation.businesses

import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef
import pimonitor.monitors.MonitorsService
import kotlin.js.JsExport

abstract class BusinessesService(
    protected open val bus: EventBus,
    protected open val monitorsService: MonitorsService,
    protected open val config: ServiceConfig
) {
    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusiness) = Event(CREATE_BUSINESS_EVENT_ID, business)
    }

    protected val scope get() = config.scope

    abstract fun all(): Later<List<MonitoredBusiness>>

    protected abstract fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ): Later<MonitoredBusiness>

    fun create(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef = monitorsService.currentMonitor.ref()
    ) = scope.later {
        val business = executeCreate(params, monitorRef).await()
        bus.dispatch(createBusinessEvent(business))
        business
    }
}