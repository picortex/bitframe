package akkounts.quickbooks.customers

internal object CustomerParser {
    private val error = "Error parsing quickbooks customer"
    fun decodeSingleFromMap(map: Map<String, Any?>) = QuickBooksCustomer(
        id = map["Id"] as? String ?: error("$error: Couldn't find json key `Id`"),
        displayName = map["DisplayName"] as? String ?: error("$error: Couldn't find json key `DisplayName`"),
        givenName = map["GivenName"] as? String ?: ""
    )

    fun decodeSingleFromJsonResponse(json: Map<String, *>) = decodeSingleFromMap(
        json["Customer"] as Map<String, Any?>
    )

    @OptIn(ExperimentalStdlibApi::class)
    fun decodeManyFromJsonResponse(response: Map<String, *>): List<QuickBooksCustomer> {
        val QueryResponse: Map<String, Any> by response
        val customersResponse = (QueryResponse["Customer"] as? List<Map<String, Any?>>) ?: listOf()
        return buildList {
            for (singleCustomer in customersResponse) {
                add(decodeSingleFromMap(singleCustomer))
            }
        }
    }
}