package pimonitor.core.business.utils.info

import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface LoadInfoRawParams {
    val businessId: String

    /**in milliseconds*/
    val start: Double

    /** in milliseconds */
    val end: Double
}

fun LoadInfoRawParams.toValidatedParams(): LoadInfoParams {
    requiredPositive(::start)
    require(start < end) { "Start timestamp, should be before (less than) end timestamp" }
    return LoadInfoParams(
        businessId = requiredNotBlank(::businessId),
        start = start,
        end = end
    )
}