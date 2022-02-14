@file:JsExport

package pimonitor.client.businesses

import bitframe.client.ServiceConfig
import kotlin.js.JsExport
import pimonitor.core.businesses.BusinessesService as CoreBusinessesService

abstract class BusinessesService(
    override val config: ServiceConfig
) : CoreBusinessesService(config) {

    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
//        fun createBusinessEvent(business: MonitoredBusiness) = Event(business,CREATE_BUSINESS_EVENT_ID )
    }
}