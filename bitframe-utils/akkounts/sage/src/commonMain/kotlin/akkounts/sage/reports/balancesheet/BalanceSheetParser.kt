package akkounts.sage.reports.balancesheet

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import akkounts.containsAny
import akkounts.reports.balancesheet.BalanceSheet
import kotlinx.collections.interoperable.toInteroperableList

class BalanceSheetParser(val entries: List<Map<String, *>>) {
    companion object {
        private val currentAssetAccountNames = listOf(
            "Cash", "Bank", "Receivable", "Stock", "Inventory"
        )
        private val fixedAssetAccountNames = listOf(
            "Property", "Vehicle", "Furniture", "Land"
        )

        private val equityAccountNames = listOf(
            "Capital", "Equity"
        )

        private val currentLiabilitiesAccountNames = listOf(
            "Payable"
        )

        private val longTermLiabilitiesAccountNames = listOf<String>()
    }

    private fun Any?.toMoney(): Int = (toString().toDouble() * 100).toInt()

    private fun filterToStatementEntry(accountNames: List<String>, debitFirst: Boolean) = entries.filter {
        it["Name"].toString().containsAny(accountNames)
    }.map {
        StatementEntryItem(
            details = it["Name"].toString(),
            amount = if (debitFirst) {
                it["Debit"].toMoney() - it["Credit"].toMoney()
            } else {
                it["Credit"].toMoney() - it["Debit"].toMoney()
            }
        )
    }.let { CategoryEntry(it.toInteroperableList()) }

    fun assets() = BalanceSheet.Data.Assets(
        current = filterToStatementEntry(currentAssetAccountNames, debitFirst = true),
        fixed = filterToStatementEntry(fixedAssetAccountNames, debitFirst = true)
    )

    fun equity(): CategoryEntry {
        val assets = assets()
        val liab = liabilities()
        val equity = filterToStatementEntry(equityAccountNames, debitFirst = false)

        if (assets.total != (equity.total + liab.total)) {
            val amount = assets.total - liab.total - equity.total
            val details = if (amount > 0) "Retained Earnings" else "Accumulated Deficit"
            return CategoryEntry((equity.items + StatementEntryItem(details, amount)).toInteroperableList())
        }

        return equity
    }

    fun liabilities() = BalanceSheet.Data.Liabilities(
        current = filterToStatementEntry(currentLiabilitiesAccountNames, debitFirst = false),
        longTerm = filterToStatementEntry(longTermLiabilitiesAccountNames, debitFirst = false)
    )
}