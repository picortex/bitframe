package presenters.fields

import kash.Money

fun Money.toInputValue() = toFormattedString(
    abbreviate = true,
    prefix = "",
    decimals = 2,
    thousandsSeparator = "",
    decimalSeparator = "."
)