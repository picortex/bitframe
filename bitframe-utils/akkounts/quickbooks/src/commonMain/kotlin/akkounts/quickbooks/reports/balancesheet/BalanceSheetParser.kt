package akkounts.quickbooks.reports.balancesheet

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.reports.utils.GenericParser
import akkounts.provider.Owner
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.utils.CategoryEntry
import akkounts.utils.unset
import datetime.toSimpleDateTime
import kash.Currency
import kotlinx.datetime.LocalDate

class BalanceSheetParser(map: Map<String, Any?>) : GenericParser(map) {

    fun parseAssets(currency: Currency): BalanceSheet.Body.Assets {
        val row = getRows(map).find {
            it["group"] == "NetAssets"
        } ?: error("Failed to get assets from balance sheet report")
        val current = getEntriesFrom("Current Assets", currency, row, "TotalAssetLessCurrentLiabilities")
        val fixed = getEntriesFrom("Fixed Assets", currency, row, "TotalLongTermAssets")
        return BalanceSheet.Body.Assets(current, fixed)
    }

    fun parseEquity(currency: Currency): CategoryEntry {
        val row = getRows(map).find {
            it["group"] == "NetLiabilitiesAndShareHolderEquity"
        } ?: error("Failed to get liabilities and shareholders equity")
        return getEntriesFrom("Equity", currency, row, "TotalShareHoldersEquityNode")
    }

    fun parseLiabilities(currency: Currency): BalanceSheet.Body.Liabilities {
        val row = getRows(map).find {
            it["group"] == "NetLiabilitiesAndShareHolderEquity"
        } ?: error("Failed to get liabilities and shareholders equity")
        val current = getEntriesFrom("Current Liabilities", currency, row, "TotalLongTermLiabilities")
        val longTerm = getEntriesFrom("Long Term Liabilities", currency, row, "TotalNonCurLiabilities")
        return BalanceSheet.Body.Liabilities(current, longTerm)
    }

    fun parseHeader() = BalanceSheet.Header(
        endOf = LocalDate.parse(header["EndPeriod"] ?: error("Couldn't get End Period")).toSimpleDateTime(),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )

    fun parseBody(currency: Currency) = BalanceSheet.Body(
        assets = parseAssets(currency),
        equity = parseEquity(currency),
        liabilities = parseLiabilities(currency),
    )

    fun parse(): BalanceSheet {
        val header = parseHeader()
        return BalanceSheet(unset, header, parseBody(header.currency))
    }
}