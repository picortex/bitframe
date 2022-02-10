@file:JsExport

package pimonitor.client.evaluation.businesses

import bitframe.service.client.config.ServiceConfig
import pimonitor.evaluation.businesses.BusinessesService
import kotlin.js.JsExport

abstract class BusinessesService(
    override val config: ServiceConfig
) : BusinessesService(config) {

    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
//        fun createBusinessEvent(business: MonitoredBusiness) = Event(business,CREATE_BUSINESS_EVENT_ID )
    }
}