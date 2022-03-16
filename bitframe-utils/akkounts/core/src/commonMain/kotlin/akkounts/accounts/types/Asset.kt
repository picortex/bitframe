package akkounts.accounts.types

import kotlinx.serialization.Serializable

@Serializable
sealed class Asset : AccountType() {
    @Serializable
    sealed class Current : Asset() {
        @Serializable
        object Bank : Current()

        @Serializable
        object Cash : Current()

        @Serializable
        object Inventory : Current()

        @Serializable
        object Receivables : Current()

        @Serializable
        object Other : Current()
    }

    @Serializable
    sealed class Fixed : Asset()
}