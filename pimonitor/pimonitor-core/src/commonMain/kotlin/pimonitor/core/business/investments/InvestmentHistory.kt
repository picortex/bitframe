@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.investments

import bitframe.core.UserRef
import datetime.SimpleDateTime
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class InvestmentHistory {

    @Serializable
    data class Created(val on: SimpleDateTime, val by: UserRef) : InvestmentHistory()

    @Serializable
    data class Disbursed(val amount: Money, val on: SimpleDateTime, val by: UserRef) : InvestmentHistory()
}