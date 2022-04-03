@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.cashflow

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import akkounts.reports.FinancialReport
import akkounts.reports.FinancialReportHeader
import datetime.SimpleDateTime
import kash.Currency
import kash.Money
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class CashFlow(
    override val uid: String,
    override val header: Header,
    override val body: Body
) : FinancialReport {
    @Serializable
    data class Header(
        override val vendor: Vendor,
        override val owner: Owner,
        override val currency: Currency,
        val start: SimpleDateTime,
        val end: SimpleDateTime
    ) : FinancialReportHeader

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