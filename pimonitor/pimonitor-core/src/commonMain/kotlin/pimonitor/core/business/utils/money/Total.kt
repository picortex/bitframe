package pimonitor.core.business.utils.money

import kash.Currency
import kash.Money

fun Iterable<Money>.sum(currency: Currency) = fold(Money(0, currency)) { acc, money -> acc + money }