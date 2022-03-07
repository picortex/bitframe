@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import pimonitor.core.accounting.Accounting
import pimonitor.core.businesses.Dashboard
import pimonitor.core.businesses.params.InviteToShareReportsParams
import kotlin.js.JsExport

@Serializable
sealed class InviteStatus(val on: Long) {
    @Serializable
    data class Sent(
        val params: InviteToShareReportsParams
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())

    @Serializable
    data class AcceptedDashboard(
        val name: Dashboard
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())

    @Serializable
    data class AcceptedAccounting(
        val name: Accounting
    ) : InviteStatus(Clock.System.now().toEpochMilliseconds())
}