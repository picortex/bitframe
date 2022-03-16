package akkounts.quickbooks.bills

import akkounts.quickbooks.vendors.VendorParams
import payments.requests.Sender

val Sender.quickBooksDisplayName get() = "$name-$uid"

fun Sender.toQuickBooksVendorParams() = VendorParams(
    givenName = name,
    displayName = quickBooksDisplayName
)