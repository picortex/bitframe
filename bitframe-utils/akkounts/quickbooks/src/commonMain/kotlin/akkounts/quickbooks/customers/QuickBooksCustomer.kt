package akkounts.quickbooks.customers

data class QuickBooksCustomer(
    /** as stored in quickbooks*/
    val id: String,
    /** is unique*/
    val displayName: String,
    val givenName: String
)