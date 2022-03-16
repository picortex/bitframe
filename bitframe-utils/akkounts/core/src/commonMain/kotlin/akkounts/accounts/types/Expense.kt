package akkounts.accounts.types

import kotlinx.serialization.Serializable

@Serializable
sealed class Expense : Equity() {
    @Serializable
    object CostOfGoodsSold : Expense()
}