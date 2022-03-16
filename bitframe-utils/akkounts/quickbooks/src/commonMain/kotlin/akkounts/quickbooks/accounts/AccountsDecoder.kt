package akkounts.quickbooks.accounts

import kash.Currency

object AccountsDecoder {
    fun decodeSingleFrom(map: Map<String, *>) = QuickBooksAccount(
        id = map["Id"].toString(),
        name = map["Name"].toString(),
        type = map["AccountType"].toString(),
        currency = Currency.valueOf((map["CurrencyRef"] as Map<String, *>)["value"].toString())
    )

    fun decodeSingleResponseFrom(map: Map<String, *>) = decodeSingleFrom(map["Account"] as Map<String, *>)

    fun decodeManyFrom(list: List<Map<String, *>>) = list.map { decodeSingleFrom(it) }

    fun decodeManyResponseFrom(map: Map<String, *>) = decodeManyFrom(
        (map["QueryResponse"] as Map<String, *>)["Account"] as? List<Map<String, *>> ?: listOf()
    )
}