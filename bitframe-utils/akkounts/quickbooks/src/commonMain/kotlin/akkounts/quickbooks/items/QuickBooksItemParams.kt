package akkounts.quickbooks.items

import kash.Currency

class QuickBooksItemParams(
    val name: String,
    val type: QuickBooksItemType,
    val amount: Long,
    val currency: Currency
)