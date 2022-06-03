package akkounts.sage.reports.balancesheet

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import akkounts.containsAny
import akkounts.reports.balancesheet.BalanceSheet
import kash.Currency
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

    private fun Any?.toMoney(currency: Currency) = currency.of(toString().toDouble())

    private fun filterToStatementEntry(name: String, currency: Currency, accountNames: List<String>, debitFirst: Boolean) = entries.filter {
        it["Name"].toString().containsAny(accountNames)
    }.map {
        StatementEntryItem(
            details = it["Name"].toString(),
            value = if (debitFirst) {
                it["Debit"].toMoney(currency) - it["Credit"].toMoney(currency)
            } else {
                it["Credit"].toMoney(currency) - it["Debit"].toMoney(currency)
            }
        )
    }.let { CategoryEntry(name, currency, it.toInteroperableList()) }

    fun assets(currency: Currency) = BalanceSheet.Body.Assets(
        current = filterToStatementEntry("Current Assets", currency, currentAssetAccountNames, debitFirst = true),
        fixed = filterToStatementEntry("Fixed Assets", currency, fixedAssetAccountNames, debitFirst = true)
    )

    fun equity(currency: Currency): CategoryEntry {
        val assets = assets(currency)
        val liab = liabilities(currency)
        val equity = filterToStatementEntry("Equity", currency, equityAccountNames, debitFirst = false)

        if (assets.total != (equity.total + liab.total)) {
            val difference = assets.total - liab.total - equity.total
            val details = if (difference.amount > 0) "Retained Earnings" else "Accumulated Deficit"
            return CategoryEntry("Equity", currency, (equity.items + StatementEntryItem(details, difference)).toInteroperableList())
        }

        return equity
    }

    fun liabilities(currency: Currency) = BalanceSheet.Body.Liabilities(
        current = filterToStatementEntry("Current Liabilities", currency, currentLiabilitiesAccountNames, debitFirst = false),
        longTerm = filterToStatementEntry("Long Term Liabilities", currency, longTermLiabilitiesAccountNames, debitFirst = false)
    )
}