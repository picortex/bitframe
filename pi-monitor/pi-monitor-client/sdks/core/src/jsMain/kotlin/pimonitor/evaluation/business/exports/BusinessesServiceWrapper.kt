@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business.exports

import pimonitor.evaluation.businesses.BusinessesService

class BusinessesServiceWrapper(service: BusinessesService) {
    val loadBusinesses: () -> Unit = { service.all() }
}