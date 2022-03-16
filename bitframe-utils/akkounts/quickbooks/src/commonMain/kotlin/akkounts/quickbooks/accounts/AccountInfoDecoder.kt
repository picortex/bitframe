package akkounts.quickbooks.accounts

import akkounts.accounts.types.AccountType
import akkounts.accounts.types.Asset

object AccountInfoDecoder {
    fun decode(type: String): AccountType = when (type) {
        "Inventory" -> Asset.Current.Inventory
        "Other Current Asset" -> Asset.Current.Other
        else -> throw RuntimeException("Failed to decode account of type $type")
    }
}