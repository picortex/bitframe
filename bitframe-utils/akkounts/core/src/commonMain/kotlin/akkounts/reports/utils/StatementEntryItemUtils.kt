package akkounts.reports.utils

fun Collection<StatementEntryItem>.total(): Int = sumOf { it.amount }