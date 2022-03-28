package pimonitor.core.business.params

import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface LoadReportRawParams {
    val businessId: String

    /**in milliseconds*/
    val start: Double

    /** in milliseconds */
    val end: Double
}

fun LoadReportRawParams.toValidatedParams(): LoadReportParams {
    requiredPositive(::start)
    require(start < end) { "Start timestamp, should be before (less than) end timestamp" }
    return LoadReportParams(
        businessId = requiredNotBlank(::businessId),
        start = start,
        end = end
    )
}