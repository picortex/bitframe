package akkounts.quickbooks.bills

import akkounts.quickbooks.accounts.QuickBooksAccount
import akkounts.quickbooks.vendors.QuickBooksVendor
import payments.requests.Bill

internal fun Bill.toQuickBooksBillItemParams(accountRef: QuickBooksAccount) = body.items.map {
    mapOf(
        "DetailType" to "AccountBasedExpenseLineDetail",
        "Amount" to it.costAfterTax / 100,
        "AccountBasedExpenseLineDetail" to mapOf(
            "AccountRef" to mapOf(
                "value" to accountRef.id
            )
        )
    )
}

internal fun Bill.billParams(bill: Bill, vendor: QuickBooksVendor, accountRef: QuickBooksAccount) = mapOf(
    "VendorRef" to mapOf(
        "value" to vendor.id
    ),
    "Line" to bill.toQuickBooksBillItemParams(accountRef)
)
