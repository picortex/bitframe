@file:JsExport

package bitframe.evaluation.businesses

import bitframe.events.Event
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import bitframe.monitored.CreateMonitoredBusinessParams
import bitframe.monitored.MonitoredBusiness
import bitframe.monitors.MonitorRef
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class BusinessesService(
    open val config: ServiceConfig
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

    @JsName("createWithMonitorRef")
    fun create(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ) = scope.later {
        val business = executeCreate(params, monitorRef).await()
        bus.dispatch(createBusinessEvent(business))
        business
    }
}