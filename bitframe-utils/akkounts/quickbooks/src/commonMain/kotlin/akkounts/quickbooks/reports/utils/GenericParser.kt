package akkounts.quickbooks.reports.utils

import akkounts.provider.Owner
import akkounts.quickbooks.QuickBooksService
import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.FinancialReportHeader
import akkounts.reports.utils.StatementEntryItem
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList

open class GenericParser(protected val map: Map<String, Any?>) {
    protected val header: Map<String, String> =
        map["Header"] as? Map<String, String> ?: error("Couldn't get header information from quickbooks")

    protected fun getRows(map: Map<String, Any?>): List<Map<String, Any?>> {
        val rows = map["Rows"] as? Map<String, Any?> ?: mapOf()
        return rows["Row"] as? List<Map<String, Any?>> ?: listOf()
    }

    protected fun parseData(currency: Currency, data: Map<String, Any?>): StatementEntryItem {
        val colData = data["ColData"] as? List<Map<String, String>> ?: error("Couldn't get ColData")
        val value = colData.lastOrNull()?.get("value")
        val amount = if (value?.isNotEmpty() == true) {
            value.toDoubleOrNull() ?: error("Couldn't retrieve amount from ColData")
        } else 0.0

        return StatementEntryItem(
            details = colData.firstOrNull()?.get("value") ?: error("Failed to get value of ColData"),
            value = currency.of(amount)
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    protected fun parseSection(currency: Currency, section: Map<String, Any?>): List<StatementEntryItem> {
        val rows = getRows(section)
        return buildList {
            for (row in rows) {
                if (row["type"] == "Data") {
                    add(parseData(currency, row))
                } else if (row["type"] == "Section") {
                    addAll(parseSection(currency, row))
                }
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    protected fun getEntriesFrom(name: String, currency: Currency, map: Map<String, Any?>, group: String): CategoryEntry {
        val rowGroup = getRows(map).firstOrNull { it["group"] == group }
        val rows = if (rowGroup != null) getRows(rowGroup) else listOf()
        val entries = buildList {
            for (row in rows) {
                if (row["type"] == "Data") {
                    add(parseData(currency, row))
                } else if (row["type"] == "Section") {
                    addAll(parseSection(currency, row))
                }
            }
        }.toInteroperableList()
        return CategoryEntry(name, currency, entries)
    }

    fun parseDurationalHeader() = FinancialReportHeader.Durational(
        start = Date.parse(header["StartPeriod"] ?: error("Couldn't get Start Period")),
        end = Date.parse(header["EndPeriod"] ?: error("Couldn't get End Period")),
        currency = Currency.valueOf(header["Currency"].toString().uppercase()),
        vendor = QuickBooksService.VENDOR,
        owner = Owner.UNSET
    )
}