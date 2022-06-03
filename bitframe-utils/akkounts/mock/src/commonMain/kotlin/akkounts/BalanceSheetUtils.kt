package akkounts

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import kash.MoneyFormatterOptions

fun CategoryEntry.toDetailString(
    tab: String,
    formatter: MoneyFormatterOptions
): String = buildString {
    appendLine("$tab$name [${total.toFormattedString(formatter)}]")
    for (item in items) {
        appendLine("$tab\t${item.value.toFormattedString(formatter)}\t${item.details}")
    }
}

fun CategoryEntry.toDetailStringNoTitle(
    formatter: MoneyFormatterOptions
): String = buildString {
    for (item in items) {
        appendLine("\t${item.value.toFormattedString(formatter)}\t${item.details}")
    }
}

fun BalanceSheet.toDetailString(
    formatter: MoneyFormatterOptions = MoneyFormatterOptions(
        abbreviate = false,
        decimals = 0,
    )
): String = buildString {
    appendLine("Assets [${body.assets.total.toFormattedString(formatter)}]")
    appendLine(body.assets.current.toDetailString("\t", formatter))
    appendLine(body.assets.fixed.toDetailString("\t", formatter))
    appendLine("Equity [${body.equity.total.toFormattedString(formatter)}]")
    appendLine(body.equity.toDetailStringNoTitle(formatter))
    appendLine("Liabilities [${body.liabilities.total.toFormattedString(formatter)}]")
    appendLine(body.liabilities.current.toDetailString("\t", formatter))
    appendLine(body.liabilities.longTerm.toDetailString("\t", formatter))
}

fun IncomeStatement.toDetailString(
    formatter: MoneyFormatterOptions = MoneyFormatterOptions(
        abbreviate = false,
        decimals = 0,
    )
): String = buildString {
    appendLine("Revenue [${body.revenue.operating.total.toFormattedString(formatter)}]")
    appendLine(body.revenue.operating.toDetailStringNoTitle(formatter))
    appendLine("Cost of Revenue [$${body.costOfRevenue.total.toFormattedString(formatter)}]")
    appendLine(body.costOfRevenue.toDetailStringNoTitle(formatter))
    appendLine("Gross Profit [${body.grossProfit.toFormattedString(formatter)}]")
    appendLine("Other Income [${body.revenue.other.total.toFormattedString(formatter)}]")
    appendLine(body.revenue.other.toDetailStringNoTitle(formatter))
    appendLine("Operating Expenses [${body.expenses.total.toFormattedString(formatter)}]")
    appendLine(body.expenses.operating.toDetailStringNoTitle(formatter))
    appendLine("Other Expenses [${body.expenses.other.total.toFormattedString(formatter)}]")
    appendLine(body.expenses.other.toDetailStringNoTitle(formatter))
    appendLine("Net Income before taxes [${body.netIncomeBeforeTaxes.toFormattedString(formatter)}]")
    appendLine("Taxes [${body.taxes.total.toFormattedString(formatter)}]")
    appendLine(body.taxes.toDetailStringNoTitle(formatter))
    appendLine("Net Income After taxes [${body.netIncomeAfterTaxes.toFormattedString(formatter)}]")
}