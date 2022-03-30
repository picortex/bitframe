package akkounts.quickbooks.reports.incomestatement

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.provider.Owner
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import akkounts.utils.unset
import kash.Currency
import kotlinx.collections.interoperable.listOf
import kotlinx.datetime.LocalDate

class IncomeStatementParser(map: Map<String, Any?>) : GenericParser(map) {
    fun parseHeader() = IncomeStatement.Header(
        start = LocalDate.parse(header["StartPeriod"] ?: error("Couldn't get Start Period")),
        end = LocalDate.parse(header["EndPeriod"] ?: error("Couldn't get End Period")),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )

    fun parseBody(currency: Currency) = IncomeStatement.Body(
        income = getEntriesFrom("Revenue", currency, map, "Income"),
        otherIncome = CategoryEntry("Other Income", currency, listOf()),
        costOfSales = getEntriesFrom("Cost of Sales", currency, map, "COGS"),
        expenses = getEntriesFrom("Expenses", currency, map, "Expenses"),
        otherExpenses = CategoryEntry("Other Expenses", currency, listOf()),
        taxes = CategoryEntry("Taxes", currency, listOf())
    )

    fun parse(): IncomeStatement {
        val header = parseHeader()
        return IncomeStatement(unset, parseHeader(), parseBody(header.currency))
    }
}