package akkounts.quickbooks.accounts

import akkounts.accounts.types.*
import akkounts.accounts.types.Asset.Current.*

object AccountInfoEncoder {
    fun encode(type: Asset): QuickBooksAccountParams.Info = when (type) {
        Bank -> TODO()
        Cash -> TODO()
        Inventory -> QuickBooksAccountParams.Info.SubType("Inventory")
        Receivables -> TODO()
        Other -> QuickBooksAccountParams.Info.SubType("OtherCurrentAssets")
    }

    fun encode(type: AccountType): QuickBooksAccountParams.Info = when (type) {
        is Asset -> encode(type)
        Liabilities.ShortTerm.Payables -> TODO()
        Income.Sales -> TODO()
        Expense.CostOfGoodsSold -> TODO()
    }
}