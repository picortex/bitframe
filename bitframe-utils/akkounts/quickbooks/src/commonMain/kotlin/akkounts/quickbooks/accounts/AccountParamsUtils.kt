package akkounts.quickbooks.accounts

import akkounts.accounts.AccountParams

fun AccountParams.toQuickBooksAccountParams() = QuickBooksAccountParams(
    name = name,
    currency = currency,
    info = AccountInfoEncoder.encode(type)
)