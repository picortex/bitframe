@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import pimonitor.core.search.SearchResult
import presenters.fields.TextInputField
import kotlin.js.JsExport

data class SearchState(
    val status: SearchFeedback = SearchFeedback.Pending,
    val field: TextInputField = TextInputField(
        name = "search",
        label = "Search . . .",
        hint = "Search everywhere",
        value = ""
    ),
    val results: List<SearchResult> = emptyList()
)
