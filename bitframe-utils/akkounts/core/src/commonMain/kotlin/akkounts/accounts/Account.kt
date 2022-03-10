package akkounts.accounts

import akkounts.accounts.types.AccountType
import kash.Currency
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: String,
    val name: String,
    val type: AccountType,
    val currency: Currency
)