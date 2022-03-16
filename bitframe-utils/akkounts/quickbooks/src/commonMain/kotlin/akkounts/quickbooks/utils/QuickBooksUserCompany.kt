package akkounts.quickbooks.utils

data class QuickBooksUserCompany(
    val uid: String,
    val name: String,
    val realmId: String,
    val refreshToken: String
)