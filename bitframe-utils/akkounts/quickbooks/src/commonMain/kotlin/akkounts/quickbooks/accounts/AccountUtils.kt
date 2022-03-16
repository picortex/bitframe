package akkounts.quickbooks.accounts

import akkounts.accounts.Account
import kash.Currency

private const val QUICKBOOKS_INVENTORY_INCOME_ACCOUNT_TYPE = "SalesOfProductIncome"
private const val QUICKBOOKS_INVENTORY_ASSET_ACCOUNT_TYPE = "Other Current Asset"
private const val QUICKBOOKS_INVENTORY_EXPENSE_ACCOUNT_TYPE = "Cost of Goods Sold"

fun InventoryItemIncomeAccountParams(name: String, currency: Currency) = QuickBooksAccountParams(
    name = name,
    info = QuickBooksAccountParams.Info.SubType(QUICKBOOKS_INVENTORY_INCOME_ACCOUNT_TYPE),
    currency = currency
)

fun InventoryItemAssetAccountParams(name: String, currency: Currency) = QuickBooksAccountParams(
    name = name,
    info = QuickBooksAccountParams.Info.Type(QUICKBOOKS_INVENTORY_ASSET_ACCOUNT_TYPE),
    currency = currency
)

fun InventoryItemExpenseAccountParam(name: String, currency: Currency) = QuickBooksAccountParams(
    name = name,
    info = QuickBooksAccountParams.Info.Type(QUICKBOOKS_INVENTORY_EXPENSE_ACCOUNT_TYPE),
    currency = currency
)

internal fun QuickBooksAccount.toAccount() = Account(
    id = id,
    name = name,
    type = AccountInfoDecoder.decode(type),
    currency = currency
)