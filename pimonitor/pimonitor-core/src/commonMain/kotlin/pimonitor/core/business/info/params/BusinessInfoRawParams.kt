package pimonitor.core.business.info.params

import kotlin.js.JsExport

@JsExport
interface BusinessInfoRawParams : BusinessInfoRawFormParams {
    val businessId: String
}

fun BusinessInfoRawParams.toValidatedParams() = toValidatedParams(businessId)