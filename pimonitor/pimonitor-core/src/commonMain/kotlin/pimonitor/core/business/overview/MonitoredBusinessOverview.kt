@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.overview

import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.changes.MoneyChangeBox
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
sealed class MonitoredBusinessOverview {
    abstract val business: MonitoredBusinessBasicInfo
    abstract val isInvited: Boolean

    @Serializable
    data class FinancialAndOperational(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val revenueVsExpenses: Chart<Money>,
        val otherChart: Chart<Money>,
        val assets: Chart<Money>,
        val balanceSheetChart: BalanceSheetChart
    ) : MonitoredBusinessOverview() {
        override val isInvited: Boolean = true
    }

    @Serializable
    data class OperationalOnly(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val revenueVsExpenses: Chart<Money>,
        val otherChart: Chart<Money>
    ) : MonitoredBusinessOverview() {
        override val isInvited: Boolean = true
    }

    @Serializable
    data class FinancialOnly(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val assets: Chart<Money>,
        val balanceSheetChart: BalanceSheetChart
    ) : MonitoredBusinessOverview() {
        override val isInvited: Boolean = true
    }

    @Serializable
    data class NotIntegrated(
        override val business: MonitoredBusinessBasicInfo,
        override val isInvited: Boolean = false
    ) : MonitoredBusinessOverview()

    val isFinancialAndOperational get() = this is FinancialAndOperational
    val asFinancialAndOperation get() = this as FinancialAndOperational

    val isOperationalOnly get() = this is OperationalOnly
    val asOperationalOnly get() = this as OperationalOnly

    val isFinancialOnly get() = this is FinancialOnly
    val asFinancialOnly get() = this as FinancialOnly

    val isNotIntegrated get() = this is NotIntegrated
    val asNotIntegrated get() = this as NotIntegrated
}