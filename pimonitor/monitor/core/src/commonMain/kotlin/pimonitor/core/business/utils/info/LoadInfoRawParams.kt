package pimonitor.core.business.utils.info

import datetime.Date
import datetime.toDate
import datetime.toTimeZone
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import validation.requiredNotBlank
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
    timeZone = timeZone?.toTimeZone()?.id ?: TimeZone.UTC.id
)

fun LoadInfoRawParams.toParsedParams(): LoadInfoParsedParams {
    val validated = toValidatedParams()
    val bId = validated.businessId
    val st = validated.start
    val en = validated.end
    val (startDate, endDate) = when {
        st != null && en != null -> {
            st.toDate() to en.toDate()
        }
        st != null && en == null -> {
            val s = st.toDate()
            val e = s + DatePeriod(days = 30)
            s to e
        }
        st == null && en != null -> {
            val e = en.toDate()
            val s = e - DatePeriod(days = 30)
            s to e
        }
        else -> { // st == null && en == null ->
            val e = Date.today(TimeZone.of(validated.timeZone))
            val s = e - DatePeriod(days = 30)
            s to e
        }
    }
    if (endDate <= startDate) throw IllegalArgumentException("The start date ($startDate) must come before the end date $endDate")
    return LoadInfoParsedParams(bId, startDate, endDate)
}