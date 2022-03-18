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

    fun parseBody() = IncomeStatement.Body(
        income = getEntriesFrom(map, "Income"),
        otherIncome = CategoryEntry(listOf()),
        costOfSales = getEntriesFrom(map, "COGS"),
        expenses = getEntriesFrom(map, "Expenses"),
        otherExpenses = CategoryEntry(listOf()),
        taxes = CategoryEntry(listOf())
    )

    fun parse() = IncomeStatement(unset, parseHeader(), parseBody())
}