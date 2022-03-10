package akkounts.quickbooks.accounts

import kash.Currency

data class QuickBooksAccount(
    val id: String,
    val name: String,
    val type: String,
    val currency: Currency
)