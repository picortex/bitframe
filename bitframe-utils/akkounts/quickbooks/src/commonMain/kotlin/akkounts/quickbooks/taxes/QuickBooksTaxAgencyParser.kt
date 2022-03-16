package akkounts.quickbooks.taxes

import akkounts.quickbooks.utils.listOf
import payments.requests.*

class QuickBooksTaxAgencyParser {

    fun parse(response: Map<String, *>): List<TaxAgency> = parseMany(rawListMap(response))

    fun rawListMap(response: Map<String, *>): List<Map<String, *>> {
        return response.listOf("TaxAgency")
    }

    fun parseMany(list: List<Map<String, *>>) = list.map {
        val errorMsg = "Couldn't find display name for tax agency"
        val name = it["DisplayName"] as? String ?: throw error(errorMsg)
        TaxAgency(name)
    }
}