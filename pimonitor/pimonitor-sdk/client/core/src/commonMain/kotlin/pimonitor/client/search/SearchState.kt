@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import pimonitor.core.search.SearchResult
import kotlin.js.JsExport

sealed class SearchState {
    object Pending : SearchState()
    data class Searching(val results: List<SearchResult> = emptyList()) : SearchState()
    data class Completed(val results: List<SearchResult> = emptyList()) : SearchState()
}
