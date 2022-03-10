package akkounts.quickbooks.accounts

import kash.Currency

data class QuickBooksAccountParams(
    val name: String,
    val info: Info,
    val currency: Currency,
) {
    sealed class Info {
        data class Type(val name: String) : Info()
        data class SubType(val name: String) : Info()
    }
}