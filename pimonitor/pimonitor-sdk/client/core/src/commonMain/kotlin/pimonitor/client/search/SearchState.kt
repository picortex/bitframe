@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import pimonitor.core.search.SearchResult
import presenters.feedbacks.Feedback
import kotlin.js.JsExport

data class SearchState(
    val loading: Feedback.Loading? = null,
    val results: List<SearchResult> = emptyList()
)
