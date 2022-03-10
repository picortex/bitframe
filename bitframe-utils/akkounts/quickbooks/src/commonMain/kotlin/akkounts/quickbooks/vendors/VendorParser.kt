package akkounts.quickbooks.vendors

internal object VendorParser {
    private val error = "Error parsing quickbooks customer"
    fun decodeSingleFromMap(map: Map<String, Any?>) = QuickBooksVendor(
        id = map["Id"] as? String ?: error("$error: Couldn't find json key `Id`"),
        displayName = map["DisplayName"] as? String ?: error("$error: Couldn't find json key `DisplayName`"),
        givenName = map["GivenName"] as? String ?: error("$error: Couldn't find json key `GivenName`")
    )

    fun decodeSingleFromJsonResponse(json: Map<String, *>) = decodeSingleFromMap(
        json["Vendor"] as Map<String, Any?>
    )

    @OptIn(ExperimentalStdlibApi::class)
    fun decodeManyFromJsonResponse(json: Map<String, *>): List<QuickBooksVendor> {
        val QueryResponse: Map<String, Any> by json
        val customersResponse = (QueryResponse["Vendor"] as? List<Map<String, Any?>>) ?: listOf()
        return buildList {
            for (singleCustomer in customersResponse) {
                add(decodeSingleFromMap(singleCustomer))
            }
        }
    }
}