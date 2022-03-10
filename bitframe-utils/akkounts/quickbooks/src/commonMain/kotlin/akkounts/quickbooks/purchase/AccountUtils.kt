package akkounts.quickbooks.purchase

import akkounts.quickbooks.accounts.QuickBooksAccountParams
import kash.Currency

private const val PURCHASE_EXPENSE_ACCOUNT_TYPE = "Expense"

fun PurchaseExpenseAccountParam(name: String, currency: Currency) = QuickBooksAccountParams(
    name = name,
    info = QuickBooksAccountParams.Info.Type(PURCHASE_EXPENSE_ACCOUNT_TYPE),
    currency = currency
)

fun DefaultPurchaseExpenseAccountParam(currency: Currency) = PurchaseExpenseAccountParam(
    name = "Payment Expenses from Other Systems",
    currency = currency
)

fun DefaultCashAccountForPurchases(currency: Currency) = QuickBooksAccountParams(
    name = "Cash For Purchases",
    info = QuickBooksAccountParams.Info.SubType("CashOnHand"),
    currency = currency
)