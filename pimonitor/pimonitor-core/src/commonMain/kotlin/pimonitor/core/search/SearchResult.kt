@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.search

import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessRef
import kotlin.js.JsExport

@Serializable
sealed class SearchResult {
    @Serializable
    data class ContactPersonSummary(
        val userId: String,
        val name: String,
        val email: String,
        val phone: String,
        val position: String,
        val business: MonitoredBusinessRef
    ) : SearchResult()

    @Serializable
    data class MonitoredBusiness(
        val uid: String,
        val name: String,
        val address: String,
        val email: String
    ) : SearchResult()
}