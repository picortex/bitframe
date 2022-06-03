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
    sealed class Reason {
        abstract val message: String

        @Serializable
        object NotIntegrated : Reason() {
            override val message: String = "Not Integrated"
            override fun toString(): String = message
        }

        @Serializable
        data class ProviderError(override val message: String) : Reason()

        val isProviderError get() = this is ProviderError
        val asProviderError get() = this as ProviderError

        val isNotIntegrated get() = this is NotIntegrated
        val asNotIntegrated get() = this as NotIntegrated
    }

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
        val otherChart: Chart<Money>,
        val missingFinancialReason: Reason = Reason.NotIntegrated
    ) : MonitoredBusinessOverview() {
        override val isInvited: Boolean = true
    }

    @Serializable
    data class FinancialOnly(
        override val business: MonitoredBusinessBasicInfo,
        val cards: List<MoneyChangeBox>,
        val assets: Chart<Money>,
        val balanceSheetChart: BalanceSheetChart,
        val missingOperationalReason: Reason = Reason.NotIntegrated
    ) : MonitoredBusinessOverview() {
        override val isInvited: Boolean = true
    }

    @Serializable
    data class MissingData(
        override val business: MonitoredBusinessBasicInfo,
        override val isInvited: Boolean = false,
        val missingFinancialReason: Reason = Reason.NotIntegrated,
        val missingOperationalReason: Reason = Reason.NotIntegrated
    ) : MonitoredBusinessOverview()

    val isFinancialAndOperational get() = this is FinancialAndOperational
    val asFinancialAndOperation get() = this as FinancialAndOperational

    val isOperationalOnly get() = this is OperationalOnly
    val asOperationalOnly get() = this as OperationalOnly

    val isFinancialOnly get() = this is FinancialOnly
    val asFinancialOnly get() = this as FinancialOnly

    val isMissingData get() = this is MissingData
    val asMissingData get() = this as MissingData
}