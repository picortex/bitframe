@file:JsExport

package pimonitor.core.businesses

import bitframe.core.ServiceConfig
import events.Event
import identifier.Email
import kotlinx.collections.interoperable.List
import later.Later
import later.later
import validation.validate
import pimonitor.core.monitored.CreateMonitoredBusinessParams
import pimonitor.core.monitored.MonitoredBusiness
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class BusinessesService(
    open val config: ServiceConfig
) {
    companion object {
        const val CREATE_BUSINESS_EVENT_TOPIC = "pimonitor.evaluation.business.create"
        fun createBusinessEvent(business: MonitoredBusiness) = Event(business, CREATE_BUSINESS_EVENT_TOPIC)
    }

    protected val scope get() = config.scope
    protected val bus get() = config.bus

    abstract fun all(): Later<List<MonitoredBusiness>>

    protected abstract fun executeCreate(
        params: CreateMonitoredBusinessParams,
    ): Later<MonitoredBusiness>

    @JsName("validateMonitoredBusinessParams")
    fun validate(params: CreateMonitoredBusinessParams) = validate {
        if (params.businessName.isBlank()) error("Business name can not be null")

        if (params.contactName.isBlank()) error("Contact name can not be null")

        Email(params.contactEmail)

        params
    }

    @JsName("createWithMonitorRef")
    fun create(
        params: CreateMonitoredBusinessParams,
    ) = scope.later {
        TODO()
    }
}