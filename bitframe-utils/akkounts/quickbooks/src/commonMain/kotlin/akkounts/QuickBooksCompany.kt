package akkounts

import kotlin.jvm.JvmOverloads

data class QuickBooksCompany @JvmOverloads constructor(
    val uid: String,
    val realmId: String,
    val refreshToken: String,
    @Deprecated("Will be removed")
    val accessToken: String = "<not a must>"
)