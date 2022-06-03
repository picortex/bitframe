package akkounts.sage.reports.incomestatement

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import akkounts.containsAny
import kash.Currency
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

    private fun Any?.toMoney(currency: Currency) = currency.of(toString().toDouble())

    fun income(name: String, currency: Currency): CategoryEntry {
        val incomeEntries = entries.filter {
            it["Name"].toString().containsAny(incomeAccountNames)
        }
        val primary = incomeEntries.map {
            val amount = it["Credit"].toMoney(currency) - it["Debit"].toMoney(currency)
            StatementEntryItem(
                details = it["Name"].toString(),
                value = amount
            )
        }
        return CategoryEntry(name, currency, primary.toInteroperableList())
    }

    fun expenses(name: String, currency: Currency): CategoryEntry {
        val expenseEntries = entries.filter {
            it["Name"].toString().containsAny(expenseAccountNames)
        }
        val primary = expenseEntries.map {
            StatementEntryItem(
                details = it["Name"].toString(),
                value = it["Debit"].toMoney(currency) - it["Credit"].toMoney(currency)
            )
        }
        return CategoryEntry(name, currency, primary.toInteroperableList())
    }

    fun taxes(name: String, currency: Currency): CategoryEntry {
        val taxEntries = entries.filter {
            it["Name"].toString().containsAny(taxesAccountNames)
        }
        val primary = taxEntries.map {
            StatementEntryItem(
                details = it["Name"].toString(),
                value = it["Credit"].toMoney(currency) - it["Debit"].toMoney(currency)
            )
        }
        return CategoryEntry(name, currency, primary.toInteroperableList())
    }
}