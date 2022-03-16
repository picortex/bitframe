package akkounts.quickbooks.customers

data class QuickBooksCustomerParams(
    val givenName: String,
    /**Should be unique across all quickbooks*/
    val displayName: String
)