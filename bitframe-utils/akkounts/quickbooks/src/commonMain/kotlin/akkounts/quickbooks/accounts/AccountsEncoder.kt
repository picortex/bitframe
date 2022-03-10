package akkounts.quickbooks.accounts

object AccountsEncoder {
    @OptIn(ExperimentalStdlibApi::class)
    fun encode(params: QuickBooksAccountParams) = buildMap<String, Any?> {
        put("Name", params.name)
        when (val info = params.info) {
            is QuickBooksAccountParams.Info.Type -> put("AccountType", info.name)
            is QuickBooksAccountParams.Info.SubType -> put("AccountSubType", info.name)
        }
        put(
            "CurrencyRef", mapOf(
                "value" to params.currency.name
            )
        )
    }
}