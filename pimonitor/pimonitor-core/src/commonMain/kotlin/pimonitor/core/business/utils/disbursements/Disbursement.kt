@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.utils.disbursements

import bitframe.core.UserRef
import datetime.SimpleDateTime
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Disbursement(
    val amount: Double,
    val date: SimpleDateTime,
    val on: SimpleDateTime,
    val by: UserRef
)