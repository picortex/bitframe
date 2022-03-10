package akkounts.accounts

import akkounts.accounts.types.AccountType
import kash.Currency

data class AccountParams(
    val name: String,
    val type: AccountType,
    val currency: Currency,
)