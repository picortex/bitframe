@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class InvestmentHistory {

    @Serializable
    data class Created(val on: Date, val by: UserRef) : InvestmentHistory()

    @Serializable
    data class Updated(val on: Date, val by: UserRef) : InvestmentHistory()

    @Serializable
    data class Disbursed(val amount: Money, val on: Date, val by: UserRef) : InvestmentHistory()
}