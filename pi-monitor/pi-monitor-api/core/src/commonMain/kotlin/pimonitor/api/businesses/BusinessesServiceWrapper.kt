@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.api.businesses

import later.then
import pimonitor.client.evaluation.businesses.BusinessesService
import kotlin.js.JsExport

class BusinessesServiceWrapper(service: BusinessesService) {
    val loadBusinesses = { service.all().then { it.toTypedArray() } }
    val create = { params: RawCreateBusinessFormParams -> service.create(params.toParams()) }
}