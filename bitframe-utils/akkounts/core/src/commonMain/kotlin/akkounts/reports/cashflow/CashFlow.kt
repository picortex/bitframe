@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.cashflow

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import akkounts.reports.FinancialReport
import akkounts.reports.utils.FinancialReportHeader
import datetime.Date
import kash.Currency
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class CashFlow(
    override val uid: String,
    override val header: FinancialReportHeader.Durational,
    override val body: Body
) : FinancialReport {
    @Serializable
    data class Body(
        val opening: Money,
        val operating: CategoryEntry,
        val investing: CategoryEntry,
        val financing: CategoryEntry
    ) {
        val closing by lazy { opening + operating.total + investing.total + financing.total }
    }
}