package akkounts.quickbooks.utils

internal fun Map<String, *>.listOf(key: String): List<Map<String, *>> {
    val response = this["QueryResponse"] as Map<String, List<Map<String, *>>>
    val errorMsg = "$key block is missing in response"
    return response[key] ?: error(errorMsg)
}