@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business.exports

import pimonitor.evaulation.business.BusinessService

class BusinessesServiceWrapper(service: BusinessService) {
    val loadBusinesses: () -> Unit = { service.all() }
}