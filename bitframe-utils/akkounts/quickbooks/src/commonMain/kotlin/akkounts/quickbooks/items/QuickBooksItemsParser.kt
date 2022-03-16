package akkounts.quickbooks.items

import akkounts.quickbooks.accounts.QuickBooksAccount
import kotlinx.serialization.mapper.Mapper

object QuickBooksItemsParser {
    private val baseError = "Failed to parse QuickBooksItem"

    fun encodeToQuickBooksParams(
        itemName: String,
        type: QuickBooksItemType,
        income: QuickBooksAccount,
        expense: QuickBooksAccount
    ) = mapOf(
        "Type" to type.name,
        "TrackQtyOnHand" to false,
        "Name" to itemName,
        "IncomeAccountRef" to mapOf(
            "value" to income.id,
            "name" to income.name
        ),
        "ExpenseAccountRef" to mapOf(
            "value" to expense.id,
            "name" to expense.name
        )
    )

    fun decodeSingleFrom(map: Map<String, *>, withAmount: Long = 0) = QuickBooksItem(
        uid = map["Id"].toString(),
        name = map["Name"].toString(),
        amount = withAmount
    )

    fun decodeSingleFrom(json: String, withAmount: Long = 0) = decodeSingleFrom(
        map = Mapper.decodeFromString(json), withAmount
    )

    fun decodeManyFrom(list: List<Map<String, *>>) = list.map {
        decodeSingleFrom(it)
    }

    fun decodeManyResponseFrom(json: String): List<QuickBooksItem> {
        val responseMap = Mapper.decodeFromString(json)["QueryResponse"] as Map<String, List<Map<String, *>>>
        val items = responseMap["Item"] ?: throw Exception("$baseError: Key Item not found")
        return decodeManyFrom(items)
    }

    fun decodeSingleResponseFrom(map: Map<String, Any?>, withAmount: Long = 0) = decodeSingleFrom(
        map["Item"] as Map<String, *>, withAmount
    )

    fun decodeSingleResponseFrom(json: String, withAmount: Long = 0) = decodeSingleResponseFrom(
        Mapper.decodeFromString(json)
    )
}