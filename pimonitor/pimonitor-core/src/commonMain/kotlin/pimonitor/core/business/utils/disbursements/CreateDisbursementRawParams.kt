package pimonitor.core.business.utils.disbursements

import bitframe.core.Session
import datetime.SimpleDateTime
import kotlin.js.JsExport

@JsExport
interface CreateDisbursementRawParams {
    val amount: Double
    val date: Double
}

fun CreateDisbursementRawParams.toDisbursement(session: Session.SignedIn) = Disbursement(
    amount = amount,
    date = SimpleDateTime(date),
    on = SimpleDateTime.now,
    by = session.user.ref()
)