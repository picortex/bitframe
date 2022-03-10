package akkounts.quickbooks.reports.utils

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import kotlinx.collections.interoperable.toInteroperableList

open class GenericParser(protected val map: Map<String, Any?>) {
    protected val header: Map<String, String> =
        map["Header"] as? Map<String, String> ?: error("Couldn't get header information from quickbooks")

    protected fun getRows(map: Map<String, Any?>): List<Map<String, Any?>> {
        val rows = map["Rows"] as? Map<String, Any?> ?: mapOf()
        return rows["Row"] as? List<Map<String, Any?>> ?: listOf()
    }

    protected fun parseData(data: Map<String, Any?>): StatementEntryItem {
        val colData = data["ColData"] as? List<Map<String, String>> ?: error("Couldn't get ColData")
        val value = colData.lastOrNull()?.get("value")
        val amount = if (value?.isNotEmpty() == true) {
            value.toDoubleOrNull() ?: error("Couldn't retrieve amount from ColData")
        } else 0.0

        return StatementEntryItem(
            details = colData.firstOrNull()?.get("value") ?: error("Failed to get value of ColData"),
            amount = (amount * 100).toInt()
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    protected fun parseSection(section: Map<String, Any?>): List<StatementEntryItem> {
        val rows = getRows(section)
        return buildList {
            for (row in rows) {
                if (row["type"] == "Data") {
                    add(parseData(row))
                } else if (row["type"] == "Section") {
                    addAll(parseSection(row))
                }
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    protected fun getEntriesFrom(map: Map<String, Any?>, group: String): CategoryEntry {
        val rowGroup = getRows(map).firstOrNull { it["group"] == group }
        val rows = if (rowGroup != null) getRows(rowGroup) else listOf()
        val entries = buildList {
            for (row in rows) {
                if (row["type"] == "Data") {
                    add(parseData(row))
                } else if (row["type"] == "Section") {
                    addAll(parseSection(row))
                }
            }
        }.toInteroperableList()
        return CategoryEntry(entries)
    }
}