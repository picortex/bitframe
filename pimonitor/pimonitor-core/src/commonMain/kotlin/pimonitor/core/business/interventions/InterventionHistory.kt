@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.interventions

import bitframe.core.UserRef
import datetime.SimpleDateTime
import kash.Money
import kotlinx.serialization.Serializable
import pimonitor.core.business.investments.InvestmentHistory
import kotlin.js.JsExport

@Serializable
sealed class InterventionHistory {

    @Serializable
    data class Created(val on: SimpleDateTime, val by: UserRef) : InterventionHistory()

    @Serializable
    data class Disbursed(val amount: Money, val on: SimpleDateTime, val by: UserRef) : InterventionHistory()
}