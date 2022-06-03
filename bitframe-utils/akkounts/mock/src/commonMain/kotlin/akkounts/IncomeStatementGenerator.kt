package akkounts

import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import kash.Currency

object IncomeStatementGenerator : CategoryEntryGenerator() {

    fun incomeStatementBodyOfZeros(
        currency: Currency
    ) = IncomeStatement.Body(
        revenue = IncomeStatement.Body.Group(
            operating = CategoryEntry("Operating Revenue", currency),
            other = CategoryEntry("Other Revenue", currency),
        ),
        costOfRevenue = CategoryEntry("Cost of Sales", currency),
        expenses = IncomeStatement.Body.Group(
            operating = CategoryEntry("Operating Expenses", currency),
            other = CategoryEntry("Other Expenses", currency),
        ),
        taxes = CategoryEntry("Taxes", currency)
    )

    private val operatingRevenues = listOf(
        "Sales",
        "Service revenue",
        "Professional fees",
        "Rent income",
        "Royalties",
        "Franchise fee",
    )


    private val otherRevenues = listOf(
        "Interest income",
        "Investment income",
        "Commission income",
    )

    private val costOfRevenue = listOf(
        "Product materials",
        "Labor",
        "Coupons",
        "Discount",
        "Promotions",
        "Commissions",
    )

    private val operatingExpenses = listOf(
        "Rent",
        "Payroll",
        "Travel",
        "Utilities",
        "Insurance",
        "Maintenance and repairs",
        "Property taxes",
        "Office supplies",
        "Depreciation",
        "Advertising"
    )

    private val otherExpenses = listOf(
        "Repairs",
        "Insurance",
        "Rates and Taxes",
        "Tax Penalties",
        "Power and Fuel",
        "Consumptions of Spares",
    )

    private val taxes = listOf(
        "Capital Gains",
        "Income Taxes",
        "Payroll Taxes",
        "Value Added Taxes",
        "Sales Taxes",
    )

    fun incomeStatementBody(
        currency: Currency,
        randomizer: Int,
    ) = IncomeStatement.Body(
        revenue = IncomeStatement.Body.Group(
            operating = entryOf("Operating Revenue", currency, randomizer, operatingRevenues) * 20.0,
            other = entryOf("Other Revenue", currency, randomizer, otherRevenues),
        ),
        costOfRevenue = entryOf("Cost of Sales", currency, randomizer, costOfRevenue),
        expenses = IncomeStatement.Body.Group(
            operating = entryOf("Operating Expenses", currency, randomizer, operatingExpenses) * 0.8,
            other = entryOf("Other Expenses", currency, randomizer, otherExpenses) * 0.8,
        ),
        taxes = entryOf("Taxes", currency, randomizer, taxes) * 0.2
    )
}