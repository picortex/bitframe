package akkounts.quickbooks.bills

import akkounts.quickbooks.accounts.QuickBooksAccountParams
import kash.Currency

fun DefaultBillsAccountParams(currency: Currency) = QuickBooksAccountParams(
    name = "Bills From External Systems",
    info = QuickBooksAccountParams.Info.Type("Expense"),
    currency = currency
)