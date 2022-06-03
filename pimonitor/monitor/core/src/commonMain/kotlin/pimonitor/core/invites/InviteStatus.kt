@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import pimonitor.core.accounting.Accounting
import pimonitor.core.businesses.DashboardFinancial
import pimonitor.core.businesses.DashboardOperational
import pimonitor.core.businesses.params.InviteToShareReportsParams
import kotlin.js.JsExport

@Serializable
sealed class InviteStatus(val on: Long) {
    @Serializable
    data class Sent(
        val params: InviteToShareReportsParams
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())

    @Serializable
    data class AcceptedOperationDashboard(
        val name: DashboardOperational
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())

    @Serializable
    data class AcceptedFinancialDashboard(
        val name: DashboardFinancial
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())
}