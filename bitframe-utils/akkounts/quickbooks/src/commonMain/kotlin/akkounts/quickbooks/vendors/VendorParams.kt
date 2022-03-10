package akkounts.quickbooks.vendors

data class VendorParams(
    val givenName: String,
    /** Should be unique across all quickbooks */
    val displayName: String
)