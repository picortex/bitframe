package akkounts.quickbooks.reports.cashflow

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.provider.Owner
import akkounts.reports.cashflow.CashFlow
import akkounts.utils.unset
import kash.Currency
import kotlinx.datetime.LocalDate

class CashFlowParser(map: Map<String, Any?>) : GenericParser(map) {
    fun parseHeader() = CashFlow.Header(
        start = LocalDate.parse(header["StartPeriod"] ?: error("Couldn't get Start Period")),
        end = LocalDate.parse(header["EndPeriod"] ?: error("Couldn't get End Period")),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )

    fun parseStartingAmount(): Int {
        val errorMsg = "Couldn't get cash at the beginning of the period"
        val row = getRows(map).find {
            it["group"] == "BeginningCash"
        } ?: error(errorMsg)
        val value = row["ColData"] as? List<Map<String, String>> ?: error(errorMsg)
        val amount = value.lastOrNull()?.get("value")?.toDouble() ?: error(errorMsg)
        return (amount * 100).toInt()
    }

    fun parseBody() = CashFlow.Data(
        startAmount = parseStartingAmount(),
        operating = getEntriesFrom(map, "OperatingActivities"),
        investing = getEntriesFrom(map, "InvestingActivities"),
        financing = getEntriesFrom(map, "FinancingActivities")
    )

    fun parse() = CashFlow(unset, parseHeader(), parseBody())
}