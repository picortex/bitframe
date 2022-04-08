@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.overview

import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.changes.MoneyChangeBox
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
sealed class MonitoredBusinessOverview {
    abstract val business: MonitoredBusinessBasicInfo

    @Serializable
    data class FinancialAndOperational(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val revenueVsExpenses: Chart<Double>,
        val otherChart: Chart<Double>,
        val assets: Chart<Double>,
        val balanceSheetChart: BalanceSheetChart
    ) : MonitoredBusinessOverview()

    @Serializable
    data class OperationalOnly(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val revenueVsExpenses: Chart<Double>,
        val otherChart: Chart<Double>
    ) : MonitoredBusinessOverview()

    @Serializable
    data class FinancialOnly(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val assets: Chart<Double>,
        val balanceSheetChart: BalanceSheetChart
    ) : MonitoredBusinessOverview()

    @Serializable
    data class NotIntegrated(
        override val business: MonitoredBusinessBasicInfo,
        val isInvited: Boolean
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