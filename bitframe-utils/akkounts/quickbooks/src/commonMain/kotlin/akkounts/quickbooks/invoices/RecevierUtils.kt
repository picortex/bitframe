package akkounts.quickbooks.invoices

import akkounts.quickbooks.customers.QuickBooksCustomerParams
import payments.requests.*

val Receiver.quickBooksDisplayName get() = "$name-$uid"

fun Receiver.toQuickBooksCustomerParams() = QuickBooksCustomerParams(
    givenName = name,
    displayName = quickBooksDisplayName
)