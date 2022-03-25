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
    val st = requiredPositive(::start)
    require(start < end) { "Start time stamp, should be less than end timestamp" }
    return LoadReportParams(
        businessId = requiredNotBlank(::businessId),
        start = st,
        end = end
    )
}