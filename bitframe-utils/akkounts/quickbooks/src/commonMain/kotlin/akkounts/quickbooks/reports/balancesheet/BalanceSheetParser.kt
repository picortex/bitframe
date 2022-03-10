package akkounts.quickbooks.reports.balancesheet

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.provider.Owner
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.utils.CategoryEntry
import akkounts.utils.unset
import kash.Currency
import kotlinx.datetime.LocalDate

class BalanceSheetParser(map: Map<String, Any?>) : GenericParser(map) {

    fun parseAssets(): BalanceSheet.Data.Assets {
        val row = getRows(map).find {
            it["group"] == "NetAssets"
        } ?: error("Failed to get assets from balance sheet report")
        val current = getEntriesFrom(row, "TotalAssetLessCurrentLiabilities")
        val fixed = getEntriesFrom(row, "TotalLongTermAssets")
        return BalanceSheet.Data.Assets(current, fixed)
    }

    fun parseEquity(): CategoryEntry {
        val row = getRows(map).find {
            it["group"] == "NetLiabilitiesAndShareHolderEquity"
        } ?: error("Failed to get liabilities and shareholders equity")
        return getEntriesFrom(row, "TotalShareHoldersEquityNode")
    }

    fun parseLiabilities(): BalanceSheet.Data.Liabilities {
        val row = getRows(map).find {
            it["group"] == "NetLiabilitiesAndShareHolderEquity"
        } ?: error("Failed to get liabilities and shareholders equity")
        val current = getEntriesFrom(row, "TotalLongTermLiabilities")
        val longTerm = getEntriesFrom(row, "TotalNonCurLiabilities")
        return BalanceSheet.Data.Liabilities(current, longTerm)
    }

    fun parseHeader() = BalanceSheet.Header(
        endOf = LocalDate.parse(header["EndPeriod"] ?: error("Couldn't get End Period")),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )

    fun parseBody() = BalanceSheet.Data(
        assets = parseAssets(),
        equity = parseEquity(),
        liabilities = parseLiabilities()
    )

    fun parse() = BalanceSheet(unset, parseHeader(), parseBody())
}