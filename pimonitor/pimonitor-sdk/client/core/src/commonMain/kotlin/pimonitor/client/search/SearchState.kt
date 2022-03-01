package pimonitor.client.search

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import pimonitor.core.search.SearchResult
import presenters.feedbacks.Feedback

data class SearchState(
    val loading: Feedback.Loading? = null,
    val results: List<SearchResult> = emptyList()
)
