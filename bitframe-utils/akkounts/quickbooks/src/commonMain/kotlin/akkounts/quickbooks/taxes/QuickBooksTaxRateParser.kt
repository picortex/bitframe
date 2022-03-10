package akkounts.quickbooks.taxes

import akkounts.quickbooks.utils.listOf

class QuickBooksTaxRateParser {
    fun parseRaw(response: Map<String, *>): List<Map<String, *>> {
        return response.listOf("TaxRate")
    }
}