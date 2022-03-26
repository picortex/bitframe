package akkounts.reports.utils

import kash.Currency

fun Collection<StatementEntryItem>.total(currency: Currency) = fold(currency.of(0)) { acc, item ->
    acc + item.value
}