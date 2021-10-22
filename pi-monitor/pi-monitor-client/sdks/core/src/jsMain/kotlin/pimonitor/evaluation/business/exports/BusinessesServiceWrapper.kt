@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business.exports

import later.then
import pimonitor.evaluation.businesses.BusinessesService

class BusinessesServiceWrapper(service: BusinessesService) {
    val loadBusinesses = { service.all().then { it.toTypedArray() } }
    val create = { params: CreateBusinessFormParams -> service.create(params.toParams()) }
}