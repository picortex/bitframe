package akkounts.accounts.types

import kotlinx.serialization.Serializable

@Serializable
sealed class Liabilities : AccountType() {
    @Serializable
    sealed class LongTerm : Liabilities()

    @Serializable
    sealed class ShortTerm : Liabilities() {
        @Serializable
        object Payables : ShortTerm()
    }
}