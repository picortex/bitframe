package akkounts.accounts.types

import kotlinx.serialization.Serializable

@Serializable
sealed class Income : Equity() {
    @Serializable
    object Sales : Income()
}