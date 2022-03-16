package akkounts.quickbooks.expenses

import akkounts.expenses.ExpenseParams
import akkounts.quickbooks.purchase.PurchaseParams

fun ExpenseParams.toPurchaseParams() = PurchaseParams(payment, currency, items)