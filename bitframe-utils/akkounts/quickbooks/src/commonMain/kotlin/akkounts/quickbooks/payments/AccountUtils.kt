package akkounts.quickbooks.payments

import akkounts.quickbooks.accounts.QuickBooksAccountParams
import kash.Currency

fun DefaultCashAccountForPayments(currency: Currency) = QuickBooksAccountParams(
    name = "Cash Received From Payments",
    info = QuickBooksAccountParams.Info.SubType("OtherCurrentAssets"),
    currency = currency
)