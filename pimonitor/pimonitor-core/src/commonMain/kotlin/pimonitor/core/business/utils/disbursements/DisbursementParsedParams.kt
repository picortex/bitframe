package pimonitor.core.business.utils.disbursements

import akkounts.utils.unset
import bitframe.core.Session
import datetime.Date
import kash.Money
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
data class DisbursementParsedParams(
    val amount: Money,
    val date: Date
) {
    fun toDisbursement(session: Session.SignedIn, timeZone: TimeZone) = Disbursement(
        uid = unset,
        amount = amount,
        date = date,
        on = Date.today(timeZone),
        by = session.user.ref()
    )
}