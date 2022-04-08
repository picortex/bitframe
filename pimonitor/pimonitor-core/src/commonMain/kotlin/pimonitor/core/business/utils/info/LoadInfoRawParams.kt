package pimonitor.core.business.utils.info

import datetime.Date
import datetime.toDate
import datetime.toTimeZone
import kotlinx.datetime.TimeZone
import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface LoadInfoRawParams {
    val businessId: String

    /**date in iso format*/
    val start: String?

    /** date in iso format */
    val end: String?

    /** the time zone your dates are in */
    val timeZone: String?
}

fun LoadInfoRawParams.toValidatedParams() = LoadInfoParams(
    businessId = requiredNotBlank(::businessId),
    start = start,
    end = end,
    timeZone = TimeZone.currentSystemDefault().id
)

fun LoadInfoRawParams.toPureParams(): LoadInfoPureParams {
    val timezone = timeZone?.toTimeZone() ?: TimeZone.UTC
    val s = start?.toDate() ?: Date.today(timezone)
    val e = end?.toDate() ?: Date.today(timezone)
    if (e <= s) throw IllegalArgumentException("The start date ($s) must come before the end date $e")
    return LoadInfoPureParams(
        businessId = requiredNotBlank(::businessId),
        start = s,
        end = e
    )
}