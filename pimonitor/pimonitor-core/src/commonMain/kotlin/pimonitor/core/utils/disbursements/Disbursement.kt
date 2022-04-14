@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursements

import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Disbursement(
    val uid: String,
    val amount: Money,
    val date: Date,
    val on: Date,
    val by: UserRef
)