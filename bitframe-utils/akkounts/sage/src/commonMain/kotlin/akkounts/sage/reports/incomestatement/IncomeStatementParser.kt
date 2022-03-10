package akkounts.sage.reports.incomestatement

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import akkounts.containsAny
import kotlinx.collections.interoperable.toInteroperableList

class IncomeStatementParser(val entries: List<Map<String, *>>) {
    companion object {
        private val incomeAccountNames = listOf(
            "Sales", "Revenue", "Income"
        )

        private val expenseAccountNames = listOf(
            "Purchase", "Expense"
        )

        private val taxesAccountNames = listOf(
            "VAT Payable"
        )
    }

    private fun Any?.toMoney(): Int = (toString().toDouble() * 100.0).toInt()

    fun income(): CategoryEntry {
        val incomeEntries = entries.filter {
            it["Name"].toString().containsAny(incomeAccountNames)
        }
        val primary = incomeEntries.map {
            StatementEntryItem(
                details = it["Name"].toString(),
                amount = it["Credit"].toMoney() - it["Debit"].toMoney()
            )
        }
        return CategoryEntry(primary.toInteroperableList())
    }

    fun expenses(): CategoryEntry {
        val expenseEntries = entries.filter {
            it["Name"].toString().containsAny(expenseAccountNames)
        }
        val primary = expenseEntries.map {
            StatementEntryItem(
                details = it["Name"].toString(),
                amount = it["Debit"].toMoney() - it["Credit"].toMoney()
            )
        }
        return CategoryEntry(primary.toInteroperableList())
    }

    fun taxes(): CategoryEntry {
        val taxEntries = entries.filter {
            it["Name"].toString().containsAny(taxesAccountNames)
        }
        val primary = taxEntries.map {
            StatementEntryItem(
                details = it["Name"].toString(),
                amount = it["Credit"].toMoney() - it["Debit"].toMoney()
            )
        }
        return CategoryEntry(primary.toInteroperableList())
    }
}