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
sealed class InviteStatus {
    @Serializable
    data class Sent(
        val params: InviteToShareReportsParams,
        val on: Long = Clock.System.now().toEpochMilliseconds()
    ) : InviteStatus()

    @Serializable
    data class AcceptedDashboard(val name: Dashboard) : InviteStatus()

    @Serializable
    data class AcceptedAccounting(val name: Accounting) : InviteStatus()
}