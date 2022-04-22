package akkounts.quickbooks.reports.incomestatement

import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import akkounts.utils.unset
import kash.Currency
import kotlinx.collections.interoperable.listOf

class IncomeStatementParser(map: Map<String, Any?>) : GenericParser(map) {
    fun parseBody(currency: Currency) = IncomeStatement.Body(
        operatingIncome = getEntriesFrom("Revenue", currency, map, "Income"),
        otherIncome = CategoryEntry("Other Income", currency, listOf()),
        costOfSales = getEntriesFrom("Cost of Sales", currency, map, "COGS"),
        operatingExpenses = getEntriesFrom("Expenses", currency, map, "Expenses"),
        otherExpenses = CategoryEntry("Other Expenses", currency, listOf()),
        taxes = CategoryEntry("Taxes", currency, listOf())
    )

    fun parse(): IncomeStatement {
        val header = parseDurationalHeader()
        return IncomeStatement(unset, parseDurationalHeader(), parseBody(header.currency))
    }
}