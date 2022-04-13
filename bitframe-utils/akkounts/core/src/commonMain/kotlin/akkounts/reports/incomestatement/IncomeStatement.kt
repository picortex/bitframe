@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.incomestatement

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import akkounts.reports.FinancialReport
import akkounts.reports.utils.FinancialReportHeader
import datetime.Date
import kash.Currency
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class IncomeStatement(
    override val uid: String,
    override val header: FinancialReportHeader.Durational,
    override val body: Body
) : FinancialReport {

    @Serializable
    data class Body(
        val revenue: Group,
        val costOfRevenue: CategoryEntry,
        val expenses: Group,
        val taxes: CategoryEntry
    ) {
        @JsName("_ignore_from")
        constructor(
            operatingIncome: CategoryEntry,
            otherIncome: CategoryEntry,
            costOfSales: CategoryEntry,
            operatingExpenses: CategoryEntry,
            otherExpenses: CategoryEntry,
            taxes: CategoryEntry
        ) : this(
            revenue = Group(operatingIncome, otherIncome),
            costOfRevenue = costOfSales,
            expenses = Group(operatingExpenses, otherExpenses),
            taxes = taxes
        )

        @Serializable
        class Group(val operating: CategoryEntry, val other: CategoryEntry) {
            val total by lazy { operating.total + other.total }
        }

        val grossProfit by lazy { revenue.operating.total - costOfRevenue.total }
        val netIncomeBeforeTaxes by lazy { (grossProfit + revenue.other.total) - expenses.total }
        val netIncomeAfterTaxes by lazy { netIncomeBeforeTaxes - taxes.total }
    }
}