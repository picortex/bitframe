@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.interventions

import bitframe.core.UserRef
import datetime.Date
import datetime.SimpleDateTime
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class InterventionHistory {

    @Serializable
    data class Created(val on: Date, val by: UserRef) : InterventionHistory()

    @Serializable
    data class Disbursed(val amount: Money, val on: SimpleDateTime, val by: UserRef) : InterventionHistory()
}