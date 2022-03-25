package akkounts.quickbooks.reports.cashflow

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.provider.Owner
import akkounts.reports.cashflow.CashFlow
import akkounts.utils.unset
import kash.Currency
import kash.Money
import kotlinx.datetime.LocalDate

class CashFlowParser(map: Map<String, Any?>) : GenericParser(map) {
    fun parseHeader() = CashFlow.Header(
        start = LocalDate.parse(header["StartPeriod"] ?: error("Couldn't get Start Period")),
        end = LocalDate.parse(header["EndPeriod"] ?: error("Couldn't get End Period")),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )

    fun parseStartingAmount(currency: Currency): Money {
        val errorMsg = "Couldn't get cash at the beginning of the period"
        val row = getRows(map).find {
            it["group"] == "BeginningCash"
        } ?: error(errorMsg)
        val value = row["ColData"] as? List<Map<String, String>> ?: error(errorMsg)
        val amount = value.lastOrNull()?.get("value")?.toDouble() ?: error(errorMsg)
        return currency.of(amount)
    }

    fun parseBody(currency: Currency) = CashFlow.Body(
        opening = parseStartingAmount(currency),
        operating = getEntriesFrom("Operating Activities", currency, map, "OperatingActivities"),
        investing = getEntriesFrom("Investing Activities", currency, map, "InvestingActivities"),
        financing = getEntriesFrom("Financing Activities", currency, map, "FinancingActivities")
    )

    fun parse(): CashFlow {
        val header = parseHeader()
        return CashFlow(unset, header, parseBody(header.currency))
    }
}