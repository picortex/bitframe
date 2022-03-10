package akkounts.quickbooks.invoices

import akkounts.quickbooks.customers.QuickBooksCustomer
import akkounts.quickbooks.vendors.QuickBooksVendor
import akkounts.quickbooks.items.QuickBooksItem
import akkounts.quickbooks.items.QuickBooksItemParams
import akkounts.quickbooks.items.QuickBooksItemType
import payments.requests.*
import kash.Currency

private fun LineItem.Product.toQuickBooksItemParams(currency: Currency) = QuickBooksItemParams(
    name = name,
    type = QuickBooksItemType.NonInventory,
    currency = currency,
    amount = costBeforeTax
)

private fun LineItem.Service.toQuickBooksItemParams(currency: Currency) = QuickBooksItemParams(
    name = details,
    type = QuickBooksItemType.Service,
    currency = currency,
    amount = costBeforeTax
)

private fun LineItem.Task.toQuickBooksItemParams(currency: Currency) = QuickBooksItemParams(
    name = details,
    type = QuickBooksItemType.Service,
    currency = currency,
    amount = costBeforeTax
)

private fun LineItem.Generic.toQuickBooksItemParams(currency: Currency) = QuickBooksItemParams(
    name = details,
    type = QuickBooksItemType.Service,
    currency = currency,
    amount = amount
)


internal fun Invoice.toQuickBooksItemParams() = body.items.map {
    when (it) {
        is LineItem.Product -> it.toQuickBooksItemParams(header.currency)
        is LineItem.Service -> it.toQuickBooksItemParams(header.currency)
        is LineItem.Task -> it.toQuickBooksItemParams(header.currency)
        is LineItem.Generic -> it.toQuickBooksItemParams(header.currency)
    }
}

private fun QuickBooksItem.toLine(taxCodeRef: String) = mapOf(
    "Id" to uid,
    "DetailType" to "SalesItemLineDetail",
    "Amount" to amount.toDouble() / 100,
    "SalesItemLineDetail" to mapOf(
        "ItemRef" to mapOf(
            "name" to name,
            "value" to uid
        ),
        "UnitPrice" to amount.toDouble() / 100,
        "Qty" to 1,
        "TaxCodeRef" to mapOf(
            "value" to taxCodeRef
        )
    )
)

internal fun Invoice.invoiceParams(
    customer: QuickBooksCustomer,
    items: List<QuickBooksItem>,
    taxCodeRef: String
) = mapOf(
    "CustomerRef" to mapOf(
        "value" to customer.id
    ),
    "CurrencyRef" to mapOf(
        "value" to header.currency.name
    ),
    "Line" to items.map { it.toLine(taxCodeRef) }
)
