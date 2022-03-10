package akkounts.quickbooks.vendors

data class QuickBooksVendor(
    /** as stored in quickbooks*/
    val id: String,
    /** is unique*/
    val displayName: String,
    val givenName: String
)