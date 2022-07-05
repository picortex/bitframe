package akkounts

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList

object BalanceSheetGenerator : CategoryEntryGenerator() {

    fun balanceSheetBodyOfZeros(
        currency: Currency
    ) = BalanceSheet.Body(
        assets = BalanceSheet.Body.Assets(
            current = CategoryEntry("Current Assets", currency),
            fixed = CategoryEntry("Current Assets", currency)
        ),
        equity = CategoryEntry("Equity", currency),
        liabilities = BalanceSheet.Body.Liabilities(
            current = CategoryEntry("Current Liabilities", currency),
            longTerm = CategoryEntry("Current Short Term", currency),
        )
    )

    private val assetList = listOf(
        "Cash",
        "Cash Equivalents",
        "Inventory",
        "Accounts Receivable",
        "Marketable Securities",
        "Prepaid Expenses",
        "Other Liquid Assets",
    )

    private val fixedAssets = listOf(
        "Buildings",
        "Computer equipment",
        "Software",
        "Furniture",
        "Land",
        "Machinery",
        "Vehicles",
    )

    private val currentLiabilities = listOf(
        "Accounts payable",
        "Interest payable",
        "Income taxes payable",
        "Bills payable",
        "Bank account overdrafts",
        "Accrued expenses",
        "Short-term loans",
    )

    private val longTermLiabilities = listOf(
        "Bonds payable",
        "Long-term notes payable",
        "Deferred tax liabilities",
        "Mortgage payable",
        "Capital leases",
    )

    private val equities = listOf(
        "Common Stock",
        "Preferred Stock",
        "Contributed Surplus",
        "Additional Paid-In Capital",
        "Treasury Stock (Contra-Equity Account)",
    )

    fun balanceSheetBody(
        currency: Currency,
        randomizer: Int,
    ): BalanceSheet.Body {
        val assets = BalanceSheet.Body.Assets(
            current = entryOf("Current Assets", currency, randomizer, assetList),
            fixed = entryOf("Fixed Assets", currency, randomizer, fixedAssets)
        )
        val liabilities = BalanceSheet.Body.Liabilities(
            current = entryOf("Current Liabilities", currency, randomizer, currentLiabilities),
            longTerm = entryOf("Long Term Liabilities", currency, randomizer, longTermLiabilities),
        )
        val impureEquity = entryOf("Equity", currency, randomizer, equities)
        val diff = assets.total - (liabilities.total + impureEquity.total)
        val entry = if (diff.amountAsInt >= 0) {
            StatementEntryItem("Retained Earning", diff)
        } else {
            StatementEntryItem("(Accumulated Deficiency)", diff)
        }
        return BalanceSheet.Body(
            assets = assets,
            equity = CategoryEntry("Equity", currency, (impureEquity.items + entry).toInteroperableList()),
            liabilities = liabilities
        )
    }
}