package akkounts.quickbooks.taxes

import akkounts.quickbooks.utils.listOf

class QuickBooksTaxCodeParser {
    fun parseRaw(response: Map<String, *>): List<Map<String, *>> {
        return response.listOf("TaxCode")
    }
}